package com.example.Omafourm.service;

import com.example.Omafourm.entity.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    void addPost();
    void deletePost();
    void updatePost();
    Post likePost(Post post);

}
