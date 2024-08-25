package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.data.dto.comment.CommentResponse;
import com.openclassrooms.mddapi.data.dto.post.*;
import com.openclassrooms.mddapi.util.enums.Direction;

import java.util.List;

public interface IPostService {
    PostDetailsResponse findPost(Long id);

    List<PostResponse> findAllPosts(Direction direction);

    PostResponse addPost(PostInput postInput);

    CommentResponse comment(Long id, PostCommentInput postCommentInput);
}
