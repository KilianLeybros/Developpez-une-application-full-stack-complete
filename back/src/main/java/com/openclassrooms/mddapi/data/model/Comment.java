package com.openclassrooms.mddapi.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="comment")
@Data
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="message")
    private String message;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private Post post;

}
