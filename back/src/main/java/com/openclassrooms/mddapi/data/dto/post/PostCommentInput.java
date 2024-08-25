package com.openclassrooms.mddapi.data.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record PostCommentInput(
        @Length(max = 255, message = "Message trop long, pas plus de 255 caractères")
        @NotBlank(message = "Le message ne peut pas être vide")
        @NotNull(message = "Le message ne peut pas être vide")
        String message
) {
}
