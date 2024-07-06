package com.openclassrooms.mddapi.data.dto.post;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record PostInput(
        @NotNull(message = "Le théme est obligatoire")
        @Min(value = 1, message = "Le théme est obligatoire")
        Long topicId,
        @Length(max = 100, message = "Titre trop long, pas plus de 100 caractères")
        @NotBlank(message = "Le titre est obligatoire")
        @NotNull(message = "Le titre est obligatoire")
        String title,

        @Length(max = 255, message = "Description trop longue, pas plus de 255 caractères")
        @NotBlank(message = "La description est obligatoire")
        @NotNull(message = "La description est obligatoire")
        String description) {
}
