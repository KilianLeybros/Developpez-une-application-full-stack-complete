package com.openclassrooms.mddapi.data.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name="topic")
@Data
public class Topic {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "topic")
    private Set<Post> posts;

}
