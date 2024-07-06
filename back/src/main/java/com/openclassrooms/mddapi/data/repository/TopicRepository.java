package com.openclassrooms.mddapi.data.repository;

import com.openclassrooms.mddapi.data.model.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {
}
