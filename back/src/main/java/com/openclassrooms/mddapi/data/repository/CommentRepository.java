package com.openclassrooms.mddapi.data.repository;

import com.openclassrooms.mddapi.data.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
