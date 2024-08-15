package com.openclassrooms.mddapi.controller;


import com.openclassrooms.mddapi.data.dto.topic.TopicResponse;
import com.openclassrooms.mddapi.service.ITopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Topic API")
@RequestMapping("api/topic")
public class TopicController {


    @Autowired
    private ITopicService topicService;

    @Operation(summary = "Find topics", description = "Permet de récupérer les topics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topics",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TopicResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAllTopics(){
        return ResponseEntity.ok(topicService.findAllTopics());
    }

   /* @Operation(summary = "Find subscribed topics", description = "Permet de récupérer les topics suivis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Topic.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping("subscribed")
    public ResponseEntity<List<SubscribedTopic>> getSubscribed(){
        return ResponseEntity.ok(topicService.findSubscribedTopics());
    }*/

    @Operation(summary = "Subscribe a topic", description = "Permet de subscribe un topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topic",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TopicResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Le topic avec l'id spécifié n'a pas été trouvé",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping("{id}/subscribe")
    public ResponseEntity<TopicResponse> subscribe(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(topicService.subscribe(id));
    }

    @Operation(summary = "Unsubscribe a topic", description = "Permet de unsubscribe un topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topic",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TopicResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Le topic avec l'id spécifié n'a pas été trouvé",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping("{id}/unsubscribe")
    public ResponseEntity<TopicResponse> unsubscribe(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(topicService.unsubscribe(id));
    }

}
