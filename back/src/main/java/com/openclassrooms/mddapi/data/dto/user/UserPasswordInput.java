package com.openclassrooms.mddapi.data.dto.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserPasswordInput(
        String currentPassword,
        @Size(min=8 ,message="Entrez un mot de passe d'au moins 8 caractères.")
        @Pattern(regexp=  "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-])[A-Za-z0-9#?!@$%^&*-]{0,}$", message="Le mot de passe doit contenir au moins une minuscule, une majuscule, un chiffre et un caractère spécial")
        String newPassword,

        String confirm
) {

}
