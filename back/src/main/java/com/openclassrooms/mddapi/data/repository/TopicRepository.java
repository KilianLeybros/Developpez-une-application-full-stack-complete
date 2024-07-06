package com.openclassrooms.mddapi.data.repository;

import com.openclassrooms.mddapi.data.model.Topic;
import com.openclassrooms.mddapi.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByUsers(Set<User> users);

}
