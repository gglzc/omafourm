package com.example.Omafourm.service.response;

import com.example.Omafourm.entity.Post;

import java.util.List;

/**
 * @param: updateResponse
 * @package: com.example.Omafourm.service.response
 * @className: updateResponse
 * @description: TODO
 * @return:
 */
public class UpdateResponse {
    private Long id;
    private String username;
    private List<Post> posts;

    public UpdateResponse(Long id, String username, List<Post> posts) {
        this.id = id;
        this.username = username;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
