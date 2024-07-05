package com.openclassrooms.mddapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/post")
public class PostController {

    @GetMapping
    public ResponseEntity<?> getAllPost(){
        return ResponseEntity.ok("ok");
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") String id){
        return ResponseEntity.ok("ok");
    }

    @PostMapping
    public ResponseEntity<?> createPost(){
        return ResponseEntity.ok("ok");
    }

    @PatchMapping
    public ResponseEntity<?> updatePost(){
        return ResponseEntity.ok("ok");
    }

    @PostMapping("{id}/comment")
    public ResponseEntity<?> commentPost(@PathVariable("id") String id){
        return ResponseEntity.ok("ok");
    }
}
