package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.data.dto.user.UserProfileInput;
import com.openclassrooms.mddapi.data.model.User;

public interface IUserService {

    User updateUserProfile(UserProfileInput userInfo);
}
