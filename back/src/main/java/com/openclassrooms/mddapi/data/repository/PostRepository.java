package com.openclassrooms.mddapi.data.repository;

import com.openclassrooms.mddapi.data.model.Post;
import com.openclassrooms.mddapi.util.enums.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    @Query(value= """
            SELECT * FROM post
            JOIN topic on post.topic_id = topic.id
            JOIN subscription on topic.id = subscription.topic_id
            JOIN _user on subscription.user_id = _user.id
            WHERE _user.id = :userId
            ORDER_BY post.created_at :direction""", nativeQuery = true)
    List<Post> findSubscribedTopicPostsOrderByDate(@Param("userId") Long userId, @Param("direction") Direction direction);
}
