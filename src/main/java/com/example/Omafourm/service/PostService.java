package com.example.Omafourm.service;

import com.example.Omafourm.entity.Post;
import com.example.Omafourm.entity.Tag;
import com.example.Omafourm.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PostService {
    List<Post> getPostByUser(User user);
    Post createPost(User user, String title, String content, Set<Tag> tags);
    void deletePost(Post post);
    Post updatePost(Post post);
    Post likePost(Post post);

}
