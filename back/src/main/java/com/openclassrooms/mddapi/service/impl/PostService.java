package com.openclassrooms.mddapi.service.impl;

import com.openclassrooms.mddapi.data.dto.comment.CommentResponse;
import com.openclassrooms.mddapi.data.dto.post.*;
import com.openclassrooms.mddapi.data.mapper.CommentMapper;
import com.openclassrooms.mddapi.data.mapper.PostMapper;
import com.openclassrooms.mddapi.data.model.Comment;
import com.openclassrooms.mddapi.data.model.Post;
import com.openclassrooms.mddapi.data.model.Topic;
import com.openclassrooms.mddapi.data.model.User;
import com.openclassrooms.mddapi.data.repository.CommentRepository;
import com.openclassrooms.mddapi.data.repository.PostRepository;
import com.openclassrooms.mddapi.data.repository.TopicRepository;
import com.openclassrooms.mddapi.service.IAuthService;
import com.openclassrooms.mddapi.service.IPostService;
import com.openclassrooms.mddapi.util.enums.Direction;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private IAuthService authService;
    @Autowired
    private TopicRepository topicRepository;

    public List<PostResponse> getAllPost(Direction direction){
        User user = authService.getCurrentUser();
        return PostMapper.toPostResponseList(postRepository.findSubscribedTopicPostsOrderByDate(user.getId(),direction.name()));
    }
    @Transactional
    public PostDetailsResponse getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("L'article demandé n'existe pas"));
        return PostMapper.toPostDetailsResponse(post);
    }

    public PostResponse addPost(PostInput postInput){
        User user = authService.getCurrentUser();
        Topic topic = topicRepository.findById(postInput.topicId()).orElseThrow(() -> new EntityNotFoundException("Le théme demandé n'existe pas"));
        Post post = new Post()
                .setTitle(postInput.title())
                .setAuthor(user)
                .setTopic(topic)
                .setDescription(postInput.description())
                .setCreatedAt(LocalDateTime.now());
        return PostMapper.toPostResponse(postRepository.save(post));
    }


    public CommentResponse comment(Long id, PostCommentInput postCommentInput){
        User user = authService.getCurrentUser();
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("L'article n'existe pas"));
        Comment comment = new Comment().setMessage(postCommentInput.message()).setPost(post).setUser(user).setCreatedAt(LocalDateTime.now());
        return CommentMapper.toCommentResponse(commentRepository.save(comment));
    }
}
