package com.openclassrooms.mddapi.data.mapper;

import com.openclassrooms.mddapi.data.dto.RegisterInput;
import com.openclassrooms.mddapi.data.model.User;

import java.time.Instant;
import java.time.LocalDateTime;


public class UserMapper {

    public static User fromRegisterInput(RegisterInput registerInput, String encodedPassword){
        return new User()
                .setEmail(registerInput.email())
                .setUsername(registerInput.username())
                .setPassword(encodedPassword)
                .setCreatedAt(LocalDateTime.now());
    }
}
