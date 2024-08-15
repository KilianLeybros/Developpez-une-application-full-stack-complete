package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.data.dto.AuthResponse;
import com.openclassrooms.mddapi.data.dto.user.UserProfileInput;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.service.IAuthService;
import com.openclassrooms.mddapi.service.IUserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IUserService userService;

    @PatchMapping(path = "info")
    public ResponseEntity<AuthResponse> updateUserProfile(@RequestBody @Valid UserProfileInput userProfileInput, HttpServletResponse response){
        User updatedUser = userService.updateUserProfile(userProfileInput);
        authService.addCookie(updatedUser.getEmail(), response);
        return ResponseEntity.ok(
                new AuthResponse(updatedUser.getId(),
                        updatedUser.getUsername(),
                        updatedUser.getEmail(),
                        updatedUser.getPassword())
        );
    }
}
