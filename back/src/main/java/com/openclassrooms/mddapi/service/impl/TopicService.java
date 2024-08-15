package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.data.dto.topic.SubscribedTopic;
import com.openclassrooms.mddapi.data.dto.topic.TopicResponse;
import com.openclassrooms.mddapi.data.mapper.TopicMapper;
import com.openclassrooms.mddapi.data.model.Topic;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.data.repository.TopicRepository;
import com.openclassrooms.mddapi.service.IAuthService;
import com.openclassrooms.mddapi.service.ITopicService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TopicService implements ITopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private IAuthService authService;

    @Transactional
    public List<TopicResponse> findAllTopics(){
        User user = authService.getCurrentUser();
        return TopicMapper.toListTopicResponse(topicRepository.findAll(), user);
    }

   /* public List<SubscribedTopic> findSubscribedTopics(){
        User user = authService.getCurrentUser();
        return TopicMapper.toListSubscribedTopic(topicRepository.findByUsers(Set.of(user)));
    }*/

    @Transactional
    public TopicResponse subscribe(Long id){
        User user = authService.getCurrentUser();
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Le théme demandé n'a pas été trouvé"));
        topic.getUsers().add(user);
        return TopicMapper.toTopicResponse(topicRepository.save(topic), true);
    }

    @Transactional
    public TopicResponse unsubscribe(Long id){
        User user = authService.getCurrentUser();
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Le théme demandé n'a pas été trouvé"));
        topic.getUsers().remove(user);
        return TopicMapper.toTopicResponse(topicRepository.save(topic), false);
    }

}
