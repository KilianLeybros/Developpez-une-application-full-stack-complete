package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.controller.exception.EmailAlreadyExistException;
import com.openclassrooms.mddapi.data.UserList;
import com.openclassrooms.mddapi.data.dto.LoginInput;
import com.openclassrooms.mddapi.data.dto.RegisterInput;
import com.openclassrooms.mddapi.data.mapper.UserMapper;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.data.repository.UserRepository;
import com.openclassrooms.mddapi.service.impl.AuthService;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;



    private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

    private IAuthService authService;

    private List<User> userList;

    @BeforeEach
    public void setUp() throws Exception {
       MockitoAnnotations.openMocks(this);
       authService = new AuthService(userRepository, passwordEncoder);
       userList = UserList.init();
    }

    @Test
    public void shouldReturnUserWhenLogin() {
        // Given
        LoginInput loginInput = new LoginInput("user1@test.com", "password");
        User user = new User()
                .setId(1L)
                .setUsername("user1")
                .setEmail("user1@test.com")
                .setPassword("password");
        when(userRepository.findByEmail("user1@test.com")).thenReturn(Optional.of(user));

        // When
        User retrievedUser = authService.login(loginInput);

        // Then
        Assertions.assertThat(retrievedUser)
                .extracting("email", "password")
                .containsExactly("user1@test.com", "password");
    }

    @Test
    public void shouldReturnUserWhenRegister() {
        // Given
        RegisterInput registerInput = new RegisterInput("user3","user3@test.com", "password");
        String encodedPassword = passwordEncoder.encode(registerInput.password());
        LocalDateTime date = LocalDateTime.now();
        User user = new User()
                .setEmail(registerInput.email())
                .setUsername(registerInput.username())
                .setPassword(encodedPassword)
                .setCreatedAt(date);

        User registeredUser = new User()
                .setId(3L)
                .setEmail(registerInput.email())
                .setUsername(registerInput.username())
                .setPassword(encodedPassword)
                .setCreatedAt(date);

        // When

        try (MockedStatic<UserMapper> mockMapper = mockStatic(UserMapper.class)){
            when(userRepository.findByEmail(registerInput.email())).thenReturn(Optional.empty());
            when(userRepository.save(user)).then(s ->{
                userList.add(s.getArgument(0));
                return registeredUser;
            });
            mockMapper.when(() -> UserMapper.fromRegisterInput(registerInput, encodedPassword)).thenReturn(user);

        User result = authService.register(registerInput);

        // Then
        assertTrue(passwordEncoder.matches(registerInput.password(), result.getPassword()));
        Assertions.assertThat(result)
                .extracting("username","email")
                .containsExactly("user3","user3@test.com");
        Assertions.assertThat(userList)
                .extracting("username")
                .containsExactly("user1", "user2", "user3");
        }
    }

    @Test
    public void shouldFailToRegister_WhenEmailAlreadyExist(){
        //Given
        RegisterInput registerInput =  new RegisterInput("user3","user1@test.com", "password");

        //When
        when(userRepository.findByEmail(registerInput.email())).thenReturn(userList.stream().findFirst());

        //Then
        assertThatThrownBy(() -> {
           authService.register(registerInput);
        }).isInstanceOf(EmailAlreadyExistException.class);
    }

    @Test
    public void shouldFailToLogin_WhenUserNotFound(){
        //Given
        LoginInput loginInput =  new LoginInput("user3@test.com", "password");

        //When
        when(userRepository.findByEmail(loginInput.email())).thenReturn(Optional.empty());

        //Then
        assertThatThrownBy(() -> {
            authService.login(loginInput);
        }).isInstanceOf(EntityNotFoundException.class);
    }

}
