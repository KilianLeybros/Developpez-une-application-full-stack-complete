package com.openclassrooms.mddapi.data.repository;

import com.openclassrooms.mddapi.data.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
