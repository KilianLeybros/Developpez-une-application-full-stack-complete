package com.openclassrooms.mddapi.data.mapper;

import com.openclassrooms.mddapi.data.dto.comment.CommentResponse;
import com.openclassrooms.mddapi.data.dto.post.PostDetailsResponse;
import com.openclassrooms.mddapi.data.dto.post.PostResponse;
import com.openclassrooms.mddapi.data.model.Post;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    public static List<PostResponse> toPostResponseList(List<Post> posts){
        return posts.stream().map(p ->
                new PostResponse(p.getId(), p.getTitle(), p.getCreatedAt(), p.getAuthor().getUsername(), p.getDescription())
        ).collect(Collectors.toList());
    }

    public static PostResponse toPostResponse(Post post){
               return new PostResponse(post.getId(), post.getTitle(), post.getCreatedAt(), post.getAuthor().getUsername(), post.getDescription());
    }

    public static PostDetailsResponse toPostDetailsResponse(Post post){
        return new PostDetailsResponse(
                post.getId(),
                post.getTitle(),
                post.getCreatedAt(),
                post.getAuthor().getUsername(),
                post.getTopic().getTitle(),
                post.getDescription(),
                post.getComments().stream().map(c -> new CommentResponse(c.getId(), c.getUser().getUsername(), c.getMessage())).collect(Collectors.toList())
                );
    }
}
