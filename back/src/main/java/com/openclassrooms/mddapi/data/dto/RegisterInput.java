package com.openclassrooms.mddapi.data.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterInput(
        @NotBlank(message = "Votre nom d'utilisateur est obligatoire")
        @Size(max = 100, message="Votre nom d'utilisateur est trop long.")
        String username,
        @NotBlank(message = "Votre adresse email est obligatoire")
        @Size(max = 100, message="L'adresse email est trop longue.")
        @Email(message = "Rentrez une adresse email valide")
        String email,
        @Size(min=8 ,message="Entrez un mot de passe d'au moins 8 caractères.")
        @Pattern(regexp=  "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-])[A-Za-z0-9#?!@$%^&*-]{0,}$", message="Le mot de passe doit contenir au moins une minuscule, une majuscule, un chiffre et un caractère spécial")
        String password


) {
}
