package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.controller.exception.BadPasswordException;
import com.openclassrooms.mddapi.data.dto.user.UserPasswordInput;
import com.openclassrooms.mddapi.data.dto.user.UserProfileInput;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.data.repository.UserRepository;
import com.openclassrooms.mddapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements IUserService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User updateUserProfile(UserProfileInput userInfo){
        User user = authService.getCurrentUser();
        user.setEmail(userInfo.email())
            .setUsername(userInfo.username())
            .setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }


    public User updatePassword(UserPasswordInput userPassword) {
        User user = authService.getCurrentUser();
        String encodedPassword = this.getNewEncodedPassword(userPassword, user);
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    private String getNewEncodedPassword(UserPasswordInput userPassword,User user) {
        if(!passwordEncoder.matches(userPassword.currentPassword(), user.getPassword()))
            throw new BadPasswordException("Le mot de passe actuel est incorrect");
        if(!userPassword.newPassword().equals(userPassword.confirm()))
            throw new BadPasswordException("Les mots de passe doivent être identiques");
        return passwordEncoder.encode(userPassword.newPassword());
    }
}
