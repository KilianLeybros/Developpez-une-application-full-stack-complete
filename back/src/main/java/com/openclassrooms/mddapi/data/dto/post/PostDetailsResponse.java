package com.openclassrooms.mddapi.data.dto.post;

import com.openclassrooms.mddapi.data.dto.comment.CommentResponse;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailsResponse(
        Long id,
        String title,
        LocalDateTime date,
        String author,
        String topic,
        String description,
        List<CommentResponse> comments
) {
}
