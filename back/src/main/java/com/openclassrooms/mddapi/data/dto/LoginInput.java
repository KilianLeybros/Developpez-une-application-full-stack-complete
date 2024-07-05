package com.openclassrooms.mddapi.data.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginInput(
        @NotBlank(message = "Veuillez entrer vos identifiants")
        String email,
        @NotBlank(message = "Veuillez entrer vos identifiants")
        String password
) {
}
