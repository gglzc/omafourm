package com.example.Omafourm.service.response;

import com.example.Omafourm.entity.Post;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @param: userResponse
 * @package: com.example.Omafourm.service.response
 * @className: userResponse
 * @description: TODO
 * @return:
 */
public class UserResponse {
    private Long id;
    private String username;
    private List<Post> posts;
    private Date create_time;
    private LocalDateTime last_login;

    public UserResponse(Long id, String username, List<Post> posts, Date create_time, LocalDateTime last_login) {
        this.id = id;
        this.username = username;
        this.posts = posts;
        this.create_time = create_time;
        this.last_login = last_login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDateTime last_login) {
        this.last_login = last_login;
    }
}
