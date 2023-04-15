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
    Post createPost(User user , String title, String content, Set<Tag> tags);
    void deletePost(Long postId ,Long userId);
    Post updatePost(User user, Post post, String title, String content, Set<Tag> tags);
    List<Post> getAllPost();
    Post likePost(Post post);
    List<Post> getAllPostTimeDesc();
    List<Post> getAllPostTimeAsc();
}
