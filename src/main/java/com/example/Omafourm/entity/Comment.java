package com.example.Omafourm.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * @param: Comment
 * @package: com.example.Omafourm.entity
 * @className: Comment
 * @description: 留言
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
    public Comment(){}
    public Comment(Long id, String content, Date createdDate, Post post) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

