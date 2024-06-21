package com.openclassrooms.mddapi.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private User author;

}
