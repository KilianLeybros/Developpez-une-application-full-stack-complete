package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.data.dto.topic.TopicResponse;

import java.util.List;

public interface ITopicService {

    List<TopicResponse> findAllTopics();

    TopicResponse subscribe(Long id);

    TopicResponse unsubscribe(Long id);
}
