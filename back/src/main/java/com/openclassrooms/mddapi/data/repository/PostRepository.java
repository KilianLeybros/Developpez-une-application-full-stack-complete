package com.openclassrooms.mddapi.data.repository;

import com.openclassrooms.mddapi.data.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value= """
            SELECT p.* FROM post p
            JOIN topic t on p.topic_id = t.id
            JOIN subscription s on t.id = s.topic_id
            JOIN _user u on s.user_id = u.id
            WHERE u.id = :userId
            ORDER BY
            CASE WHEN :direction = "ASC" THEN p.created_at
            END asc,
            CASE WHEN :direction = "DESC" THEN p.created_at
            END desc""", nativeQuery = true)
    List<Post> findSubscribedTopicPostsOrderByDate(@Param("userId") Long userId, @Param("direction") String direction);

}
