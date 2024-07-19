package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.data.dto.comment.CommentResponse;
import com.openclassrooms.mddapi.data.dto.post.*;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.util.enums.Direction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(@RequestParam(value = "direction") Direction direction){
        return ResponseEntity.ok(postService.findAllPosts(direction));
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDetailsResponse> getPost(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.findPost(id));
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody @Valid PostInput postInput){
        return ResponseEntity.ok(postService.addPost(postInput));
    }


    @PostMapping("{id}/comment")
    public ResponseEntity<CommentResponse> commentPost(@PathVariable("id") Long id, @RequestBody @Valid PostCommentInput postCommentInput){
        return ResponseEntity.ok(postService.comment(id, postCommentInput));
    }
}
