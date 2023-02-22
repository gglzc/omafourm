package com.example.Omafourm.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * @param: Comment
 * @package: com.example.Omafourm.entity
 * @className: Comment
 * @description: TODO
 * @return:
 */
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}

