package com.openclassrooms.mddapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/topic")
public class TopicController {

    @GetMapping
    public String getAllTopic(){
        return "Ok";
    }

    @GetMapping
    public String getSubscribed(){
        return "OK";
    }

}
