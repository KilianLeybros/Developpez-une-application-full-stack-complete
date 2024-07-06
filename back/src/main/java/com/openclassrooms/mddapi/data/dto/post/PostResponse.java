package com.openclassrooms.mddapi.data.dto.post;

import java.time.LocalDateTime;

public record PostResponse(Long id, String title, LocalDateTime date, String author, String description) {
}
