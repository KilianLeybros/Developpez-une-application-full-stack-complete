package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.data.dto.topic.TopicSelectListResponse;

public interface ITopicService {

    TopicSelectListResponse getTopic(Long id);
}
