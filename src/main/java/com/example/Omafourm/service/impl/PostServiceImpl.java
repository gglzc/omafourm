package com.example.Omafourm.service.impl;

import com.example.Omafourm.entity.Post;
import com.example.Omafourm.entity.Tag;
import com.example.Omafourm.entity.User;
import com.example.Omafourm.repository.PostRepository;
import com.example.Omafourm.repository.UserRepository;
import com.example.Omafourm.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @param: PostServiceImpl
 * @package: com.example.Omafourm.service.impl
 * @className: PostServiceImpl
 * @description: TODO
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Post> getPostByUser(User user) {
       return postRepository.findByUser(user);
    }

    @Override
    public Post createPost(User user, String title, String content, Set<Tag>tags) {
        user.getPosts();
            Post post = new Post();
            post.setUser(user);
            post.setCreatedDate(new Date());
            post.setUpdateDate(new Date());
            post.setTitle(title);
            post.setContent(content);
            post.setTags(tags);
            return postRepository.save(post);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Post updatePost(Post post) {
        post.setTitle(post.getTitle());
        post.setUpdateDate(new Date());
        post.setContent(post.getContent());
        return post;
    }

    @Override
    public Post likePost(Post post) {
        return null;
    }
}
