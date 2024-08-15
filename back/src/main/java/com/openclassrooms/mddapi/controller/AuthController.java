package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.data.dto.AuthResponse;
import com.openclassrooms.mddapi.data.dto.LoginInput;
import com.openclassrooms.mddapi.data.dto.RegisterInput;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Auth API")
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @Operation(summary = "Register", description = "Permet de s'enregistrer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enregistrement éffectué avec succès",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Body de la requête invalide",
                    content = {@Content(mediaType = "application/json")})
    })
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

    @Operation(summary = "Logout", description = "Permet de se déconnecter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Déconnexion éffectué avec succès"),
    })
    @PostMapping("/logout")
    public void logout(){}

    @Operation(summary = "Login", description = "Permet de se connecter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connexion éffectué avec succès",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Body de la requête invalide",
                    content = {@Content(mediaType = "application/json")})
    })
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

    @Operation(summary = "Authenticated", description = "Permet de récupérer l'utilisateur actuellement connecté")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur récupéré avec succès",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
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
