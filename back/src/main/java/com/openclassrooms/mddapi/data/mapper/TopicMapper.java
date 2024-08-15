package com.openclassrooms.mddapi.data.mapper;

import com.openclassrooms.mddapi.data.dto.topic.SubscribedTopic;
import com.openclassrooms.mddapi.data.dto.topic.TopicResponse;
import com.openclassrooms.mddapi.data.model.Topic;
import com.openclassrooms.mddapi.data.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TopicMapper {
    public static List<TopicResponse> toListTopicResponse(Collection<Topic> topics, User user){
        return topics.stream().map(t -> new TopicResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getUsers().contains(user)
        )).collect(Collectors.toList());
    }

    /*public static List<SubscribedTopic> toListSubscribedTopic(List<Topic> topics){
        return topics.stream().map(t -> new SubscribedTopic(
                t.getId(),
                t.getTitle(),
                t.getDescription()
        )).collect(Collectors.toList());
    }*/

    public static TopicResponse toTopicResponse(Topic topic, Boolean subscribed){
        return new TopicResponse(
                topic.getId(),
                topic.getTitle(),
                topic.getDescription(),
                subscribed);
    }
}
