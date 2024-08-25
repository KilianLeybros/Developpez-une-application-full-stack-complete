package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.data.dto.AuthResponse;
import com.openclassrooms.mddapi.data.dto.user.UserPasswordInput;
import com.openclassrooms.mddapi.data.dto.user.UserProfileInput;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.service.IAuthService;
import com.openclassrooms.mddapi.service.IUserService;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="User API")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IUserService userService;

    @Operation(summary = "Update user profile", description = "Permet de modifier les informations de l'utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La mise à jour de l'utilisateur c'est éffectué avec succès",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Body de la requête invalide",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
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

    @Operation(summary = "Update user password", description = "Permet de modifier le mot de passe de l'utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La mise à jour du mot de passe c'est éffectué avec succès",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Body de la requête invalide",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Le mot de passe actuel est incorrect",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Les mots de passe doivent être identiques",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PatchMapping(path = "password")
    public ResponseEntity<AuthResponse> updatePassword(@RequestBody @Valid UserPasswordInput userPasswordInput){
        User updatedUser = userService.updatePassword(userPasswordInput);
        return ResponseEntity.ok(
                new AuthResponse(updatedUser.getId(),
                        updatedUser.getUsername(),
                        updatedUser.getEmail(),
                        updatedUser.getPassword())
        );
    }
}
