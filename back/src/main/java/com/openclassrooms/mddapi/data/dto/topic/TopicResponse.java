package com.openclassrooms.mddapi.data.dto.topic;

public record TopicResponse(Long id, String title, String description, Boolean isSubscribed) {
}
