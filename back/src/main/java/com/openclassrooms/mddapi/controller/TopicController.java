package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.data.dto.topic.SubscribedTopic;
import com.openclassrooms.mddapi.data.dto.topic.TopicResponse;
import com.openclassrooms.mddapi.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/topic")
public class TopicController {


    @Autowired
    private ITopicService topicService;
    @GetMapping
    public ResponseEntity<List<TopicResponse>> getAllTopics(){
        return ResponseEntity.ok(topicService.findAllTopics());
    }

    @GetMapping("subscribed")
    public ResponseEntity<List<SubscribedTopic>> getSubscribed(){
        return ResponseEntity.ok(topicService.findSubscribedTopics());
    }

    @PostMapping("{id}/subscribe")
    public ResponseEntity<TopicResponse> subscribe(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(topicService.subscribe(id));
    }

    @PostMapping("{id}/unsubscribe")
    public ResponseEntity<TopicResponse> unsubscribe(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(topicService.unsubscribe(id));
    }

}
