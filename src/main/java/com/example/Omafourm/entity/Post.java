package com.example.Omafourm.entity;

import jakarta.persistence.*;

import java.util.*;

/**
 * @param: Post
 * @package: com.example.Omafourm.entity
 * @className: Post
 * @description: TODO
 * @return:
 */
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
}
