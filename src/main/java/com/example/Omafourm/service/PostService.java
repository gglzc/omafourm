package com.example.Omafourm.service;

import com.example.Omafourm.entity.Post;
import com.example.Omafourm.entity.Tag;
import com.example.Omafourm.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PostService {
    Post getPost(Long id);
    List<Post> getPostByUser(User user);
    Post createPost( String title, String content, Set<Tag> tags);
    void deletePost(User user ,Post post);
    Post updatePost(User user, Post post, String title, String content, Set<Tag> tags);
    Post likePost(Post post);
}
