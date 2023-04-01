package com.example.Omafourm.service.impl;

import com.example.Omafourm.entity.Post;
import com.example.Omafourm.entity.Tag;
import com.example.Omafourm.entity.User;
import com.example.Omafourm.repository.PostRepository;
import com.example.Omafourm.repository.UserRepository;
import com.example.Omafourm.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private  static  final Logger logger= LoggerFactory.getLogger(PostServiceImpl.class);
    private
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Post> getPostByUser(User user) {
       return postRepository.findByUser(user);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findPostsById(id);
    }

    @Override
    public Post createPost(String title, String content, Set<Tag>tags ) {
        User user = new User();
        Post post = new Post();
            post.setUser(user);
            post.setCreatedDate(LocalDateTime.now());
            post.setUpdateDate(LocalDateTime.now());
            post.setTitle(title);
            post.setContent(content);
            post.setTags(tags);

            List<Post> posts=user.getPosts();
            posts.add(post);
            user.setPosts(posts);
            userRepository.save(user);
            return postRepository.save(post);
    }

    @Override
    public void deletePost(User user,Post post) {
        // check post是否為本人
        if(user.getId() == post.getUser().getId()){
            postRepository.delete(post);
        }else{
            logger.warn("user :%s is delete post fail",user.getUsername());
        }
    }

    @Override
    public Post updatePost(User user, Post post, String title, String content, Set<Tag> tags) {
        if (user.getId() == post.getUser().getId()) {
            post.setTitle(title);
            post.setContent(content);
            post.setTags(tags);
            post.setUpdateDate(LocalDateTime.now());
            postRepository.save(post);
            return post;
        } else {
            logger.warn("User %s failed to update post with ID %d", user.getUsername(), post.getId());
            return null;
        }
    }
    //先不做like post 系統
    @Override
    public Post likePost(Post post) {
        return null;
    }
}
