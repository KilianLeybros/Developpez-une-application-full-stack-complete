package com.openclassrooms.mddapi.data.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserProfileInput(
        @NotBlank(message = "Votre nom d'utilisateur est obligatoire")
        @Size(max = 100, message="Votre nom d'utilisateur est trop long.")
        String username,
        @NotBlank(message = "Votre adresse email est obligatoire")
        @Size(max = 100, message="L'adresse email est trop longue.")
        @Email(message = "Rentrez une adresse email valide")
        String email
) {

}
