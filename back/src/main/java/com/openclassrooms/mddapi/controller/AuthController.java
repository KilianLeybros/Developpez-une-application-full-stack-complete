package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.data.dto.AuthResponse;
import com.openclassrooms.mddapi.data.dto.LoginInput;
import com.openclassrooms.mddapi.data.dto.RegisterInput;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.service.IAuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterInput registerInput, HttpServletResponse response){
        User registeredUser = authService.register(registerInput);
        authService.authenticate(registeredUser.getEmail(), registerInput.password(), response);
        return ResponseEntity.ok(
                new AuthResponse(registeredUser.getId(),
                        registeredUser.getUsername(),
                        registeredUser.getEmail(),
                        registeredUser.getPassword())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginInput loginInput, HttpServletResponse response){
        User loggedInUser = authService.login(loginInput);
        authService.authenticate(loggedInUser.getEmail(), loginInput.password(), response);
        return ResponseEntity.ok(
                new AuthResponse(loggedInUser.getId(),
                        loggedInUser.getUsername(),
                        loggedInUser.getEmail(),
                        loggedInUser.getPassword())
        );
    }

    @GetMapping("/authenticated")
    public ResponseEntity<AuthResponse> getCurrentUser(){
        User user = authService.getCurrentUser();
        if(user != null){
            return ResponseEntity.ok(
                    new AuthResponse(user.getId(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getPassword())
            );
        }
        return null;
    }

}
