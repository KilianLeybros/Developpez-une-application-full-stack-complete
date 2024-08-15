package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.data.dto.comment.CommentResponse;
import com.openclassrooms.mddapi.data.dto.post.*;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.util.enums.Direction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Post API")
@RequestMapping("api/post")
public class PostController {

    @Autowired
    private IPostService postService;

    @Operation(summary = "Find posts", description = "Permet de lister tout les posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(@RequestParam(value = "direction") Direction direction){
        return ResponseEntity.ok(postService.findAllPosts(direction));
    }

    @Operation(summary = "Find post by id", description = "Permet de récupérer un post avec son id et ses détails")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostDetailsResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Le post avec l'id spécifié n'a pas été trouvé",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping("{id}")
    public ResponseEntity<PostDetailsResponse> getPost(@PathVariable("id") Long id){
        return ResponseEntity.ok(postService.findPost(id));
    }

    @Operation(summary = "Create new post", description = "Permet de créer un nouveau post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post créé avec succès",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PostResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Body de la requête invalide",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody @Valid PostInput postInput){
        return ResponseEntity.ok(postService.addPost(postInput));
    }

    @Operation(summary = "Comment a post", description = "Permet de commenter un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Commentaire ajouté avec succès",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Body de la requête invalide",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Le post avec l'id spécifié n'a pas été trouvé",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping("{id}/comment")
    public ResponseEntity<CommentResponse> commentPost(@PathVariable("id") Long id, @RequestBody @Valid PostCommentInput postCommentInput){
        return ResponseEntity.ok(postService.comment(id, postCommentInput));
    }
}
