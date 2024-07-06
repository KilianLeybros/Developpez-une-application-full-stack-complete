package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.controller.exception.EmailAlreadyExistException;
import com.openclassrooms.mddapi.data.dto.LoginInput;
import com.openclassrooms.mddapi.data.dto.RegisterInput;
import com.openclassrooms.mddapi.data.mapper.UserMapper;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.data.repository.UserRepository;
import com.openclassrooms.mddapi.security.service.JwtService;
import com.openclassrooms.mddapi.service.IAuthService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {


    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Value("${jwt.cookieDuration}")
    private int cookieExpiration;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterInput registerInput){
        userRepository.findByEmail(registerInput.email()).ifPresent((user) -> {
            throw new EmailAlreadyExistException("Un compte avec l'adresse email " + registerInput.email() + " existe déja");
        });
        String encodedPassword = passwordEncoder.encode(registerInput.password());
        return userRepository.save(UserMapper.fromRegisterInput(registerInput, encodedPassword));
    }

    public User login(LoginInput loginInput){
        return userRepository.findByEmail(loginInput.email()).orElseThrow(() ->
                new EntityNotFoundException("Cette adresse email ne correspond à aucun compte.")
        );
    }


    public void authenticate(String email, String password, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        if(authentication.isAuthenticated()) {
            String token = jwtService.generateToken(email);
            // set token to cookie header
            ResponseCookie cookie = ResponseCookie.from("token", token)
                    .httpOnly(true)
                    .secure(false)
                    .sameSite("Lax")
                    .path("/")
                    .maxAge(cookieExpiration)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }
    }

    private UserDetails getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    public User getCurrentUser(){
        return userRepository.findByEmail(getAuthenticatedUser().getUsername()).orElseThrow(
                () -> new EntityNotFoundException("l'utilisateur n'a pas été trouvé")
        );
    }


}
