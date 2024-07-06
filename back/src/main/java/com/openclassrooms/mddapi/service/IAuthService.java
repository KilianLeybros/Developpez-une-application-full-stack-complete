package com.openclassrooms.mddapi.service;


import com.openclassrooms.mddapi.data.dto.LoginInput;
import com.openclassrooms.mddapi.data.dto.RegisterInput;
import com.openclassrooms.mddapi.data.model.User;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthService {
    User register(RegisterInput registerInput);
    User login(LoginInput loginInput);

    void authenticate(String email, String password, HttpServletResponse response);

    User getCurrentUser();

}
