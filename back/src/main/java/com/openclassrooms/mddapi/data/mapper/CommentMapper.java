package com.openclassrooms.mddapi.data.mapper;

import com.openclassrooms.mddapi.data.dto.comment.CommentResponse;
import com.openclassrooms.mddapi.data.model.Comment;

public class CommentMapper {

    public static CommentResponse toCommentResponse(Comment comment){
        return new CommentResponse(comment.getId(), comment.getUser().getUsername(), comment.getMessage());
    }
}
