package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.data.dto.user.UserProfileInput;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.data.repository.UserRepository;
import com.openclassrooms.mddapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    public User updateUserProfile(UserProfileInput userInfo){
        User user = authService.getCurrentUser();
        user.setEmail(userInfo.email())
            .setUsername(userInfo.username());
        return userRepository.save(user);
    }
}
