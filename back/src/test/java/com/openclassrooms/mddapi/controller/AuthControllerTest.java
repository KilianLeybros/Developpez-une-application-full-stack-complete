package com.openclassrooms.mddapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.openclassrooms.mddapi.controller.exception.EmailAlreadyExistException;
import com.openclassrooms.mddapi.data.dto.LoginInput;
import com.openclassrooms.mddapi.data.dto.RegisterInput;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.security.JwtFilter;
import com.openclassrooms.mddapi.service.AuthService;
import jakarta.persistence.EntityNotFoundException;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(
        controllers = AuthController.class,
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        value = WebSecurityConfigurer.class
                ),
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        value = JwtFilter.class
                )},
        excludeAutoConfiguration = {SecurityAutoConfiguration.class}
)
public class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    ObjectMapper objectMapper = new ObjectMapper()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .registerModule(new JavaTimeModule());

    ObjectWriter objectWriter = objectMapper.writer();
    @Test
    public void shouldNotRegister400_WhenEmailFormatIsNotValid() throws Exception{
        //Given
        RegisterInput registerInput = new RegisterInput("user1", "invalidemail", "azertyAZERTY123$");
        String content = objectWriter.writeValueAsString(registerInput);

        //When // Then
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email", CoreMatchers.is("Rentrez une adresse email valide")));
    }

    @Test
    public void shouldNotRegister400_WhenPasswordFormatIsNotValid() throws Exception{
        //Given
        RegisterInput registerInput = new RegisterInput("user1", "user1@test.com", "invalidpassword");
        String content = objectWriter.writeValueAsString(registerInput);

        //When // Then
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.password", CoreMatchers.is("Le mot de passe doit contenir au moins une minuscule, une majuscule, un chiffre et un caractère spécial")));
    }


    @Test
    public void shouldNotRegister400_WhenEmailAlreadyExist() throws Exception{
        //Given
        RegisterInput registerInput = new RegisterInput("user1", "user1@test.com", "azertyAZERTY123$");
        String content = objectWriter.writeValueAsString(registerInput);

        //When
        when(authService.register(registerInput)).thenThrow(new EmailAlreadyExistException("Un compte avec l'adresse email " + registerInput.email() + " existe déja"));

        // Then
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", CoreMatchers.is("Un compte avec l'adresse email " + registerInput.email() + " existe déja")));
    }



    @Test
    public void shouldLogin() throws Exception{
        //Given
        LoginInput loginInput = new LoginInput("user1@test.com", "azertyAZERTY123$");
        String content = objectWriter.writeValueAsString(loginInput);
        User user = new User()
                .setId(1L)
                .setUsername("user1")
                .setEmail("user1@test.com")
                .setPassword("encryptedPassword");

        //When
        when(authService.login(loginInput)).thenReturn(user);

        //Then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(user.getId().intValue())))
                .andExpect(jsonPath("$.username", CoreMatchers.is(user.getUsername())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(user.getEmail())))
                .andExpect(jsonPath("$.password", CoreMatchers.is(user.getPassword())));
    }

    @Test
    public void shouldNotLogin404_WhenUserNotFound() throws Exception{
        //Given
        LoginInput loginInput = new LoginInput("user1@test.com", "azertyAZERTY123$");
        String content = objectWriter.writeValueAsString(loginInput);

        //When
        when(authService.login(loginInput)).thenThrow(new EntityNotFoundException("Mauvais email/password"));

        //Then
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldRegister() throws Exception{
        //Given
        RegisterInput registerInput = new RegisterInput("user1", "user1@test.com", "azertyAZERTY123$");
        String content = objectWriter.writeValueAsString(registerInput);
        User user = new User()
                .setId(1L)
                .setUsername("user1")
                .setEmail("user1@test.com")
                .setPassword("encryptedPassword");

        //When
        when(authService.register(registerInput)).thenReturn(user);

        // Then
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(user.getId().intValue())))
                .andExpect(jsonPath("$.username", CoreMatchers.is(user.getUsername())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(user.getEmail())))
                .andExpect(jsonPath("$.password", CoreMatchers.is(user.getPassword())));
    }

    @Test
    public void shouldRetrieveCurrentUser() throws Exception{
        //Given
        String userEmail = "user1@test.com";
        User user = new User()
                .setId(1L)
                .setUsername("user1")
                .setEmail("user1@test.com")
                .setPassword("encryptedPassword");

        //When
        when(authService.getCurrentUser()).thenReturn(user);

        //Then
        mockMvc.perform(get("/api/auth/authenticated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(user.getId().intValue())))
                .andExpect(jsonPath("$.username", CoreMatchers.is(user.getUsername())))
                .andExpect(jsonPath("$.email", CoreMatchers.is(user.getEmail())))
                .andExpect(jsonPath("$.password", CoreMatchers.is(user.getPassword())));
    }

    @Test
    public void shouldFailRetrieveCurrentUser404() throws Exception{
        //When
        when(authService.getCurrentUser()).thenThrow(new EntityNotFoundException("l'utilisateur n'a pas été trouvé"));

        //Then
        mockMvc.perform(get("/api/auth/authenticated")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", CoreMatchers.is("l'utilisateur n'a pas été trouvé")));

    }
}
