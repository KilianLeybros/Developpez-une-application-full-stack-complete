package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.data.dto.topic.SubscribedTopic;
import com.openclassrooms.mddapi.data.dto.topic.TopicResponse;

import java.util.List;

public interface ITopicService {

    //TopicSelectListResponse getTopic(Long id);
    List<TopicResponse> findAllTopics();

    //List<SubscribedTopic> findSubscribedTopics();

    TopicResponse subscribe(Long id);

    TopicResponse unsubscribe(Long id);
}
