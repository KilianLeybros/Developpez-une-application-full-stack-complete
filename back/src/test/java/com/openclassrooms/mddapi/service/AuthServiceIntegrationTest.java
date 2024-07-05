package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.controller.exception.EmailAlreadyExistException;
import com.openclassrooms.mddapi.data.dto.LoginInput;
import com.openclassrooms.mddapi.data.dto.RegisterInput;
import com.openclassrooms.mddapi.data.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AuthServiceIntegrationTest {

    @Autowired
    private AuthService authService;


    @Test
    public void shouldLogin(){
        //Given
        LoginInput loginInput = new LoginInput("user1@test.com", "azertyAZERTY123$");

        //When
        User user = authService.login(loginInput);

        //Then
        org.junit.jupiter.api.Assertions.assertNotNull(user);
        Assertions.assertThat(user.getEmail()).isEqualTo(loginInput.email());
        Assertions.assertThat(user.getUsername()).isEqualTo("user1");
    }

    @Test
    public void shouldRegister(){
        //Given
        RegisterInput registerInput = new RegisterInput("user3","user3@test.com", "azertyAZERTY123$");

        //When
        User user = authService.register(registerInput);

        //Then
        org.junit.jupiter.api.Assertions.assertNotNull(user);
        org.junit.jupiter.api.Assertions.assertNotNull(user.getCreatedAt());
        Assertions.assertThat(user.getEmail()).isEqualTo(registerInput.email());
        Assertions.assertThat(user.getUsername()).isEqualTo("user3");
    }

    @Test
    public void shouldFailRegister(){
        //Given
        RegisterInput registerInput = new RegisterInput("user1","user1@test.com", "azertyAZERTY123$");

        //When/Then
        assertThatThrownBy(() -> {
            authService.register(registerInput);
        }).isInstanceOf(EmailAlreadyExistException.class);
    }

    @Test
    public void shouldFailLogin(){
        //Given
        LoginInput loginInput = new LoginInput("user4@test.com", "azertyAZERTY123$");

        //When/Then
        assertThatThrownBy(() -> {
            authService.login(loginInput);
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @WithMockUser(username = "user1@test.com")
    public void shouldGetCurrentUser(){
        //Given
        String email =  "user1@test.com";

        //When
        User user = authService.getCurrentUser();

        //Then
        org.junit.jupiter.api.Assertions.assertNotNull(user);
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
        Assertions.assertThat(user.getUsername()).isEqualTo("user1");

    }

    @Test
    @WithMockUser(username = "user4@test.com")
    public void shouldFailGetCurrentUser(){
        //When/Then
        assertThatThrownBy(() -> {
            authService.getCurrentUser();
        }).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    public void shouldAuthenticate(){
        //Given
        MockHttpServletResponse response = new MockHttpServletResponse();
        String email =  "user1@test.com";
        String password = "azertyAZERTY123$";

        //When
        authService.authenticate(email, password, response);

        //Then
        org.junit.jupiter.api.Assertions.assertNotNull(response.getCookie("token"));

    }

    @Test
    public void shouldFailAuthenticate(){
        //Given
        MockHttpServletResponse response = new MockHttpServletResponse();
        String email =  "user1@test.com";
        String password = "azertyAZERTY123";

        //When/Then
        assertThatThrownBy(() -> {
            authService.authenticate(email, password, response);
        }).isInstanceOf(BadCredentialsException.class);

    }
}
