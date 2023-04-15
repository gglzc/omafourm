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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @param: PostServiceImpl
 * @package: com.example.Omafourm.service.impl
 * @className: PostServiceImpl
 * @description: TODO
 */
@Service
public class PostServiceImpl implements PostService {
    private  static  final Logger logger= LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Post> getPostByUser(User user) {
       return postRepository.findByUser(user);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findPostsById(id);
    }

    @Override
    public Post createPost(User user ,String title, String content, Set<Tag>tags ) {
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
    @Transactional
    public void deletePost(Long postId,Long userId) {
       Post post = postRepository.findPostsById(postId);
       User user = userRepository.findUserById(userId);

       if (post == null || user == null) {
            throw new IllegalArgumentException("文章或用戶不存在");
        }
       if(!post.getUser().equals(userId) && user.getRole().equals("admin") ){
           throw new IllegalArgumentException("您沒有權限刪除此文章");
       }

        // 嘗試刪除帖子，處理任何可能的異常
        try {
            postRepository.delete(post);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("刪除失敗，請稍後再試");
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

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }


    //先不做like post 系統
    @Override
    public Post likePost(Post post) {
        return null;
    }

    @Override
    //文章時間由新到舊
    public List<Post> getAllPostTimeDesc() {
        return postRepository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    //文章時間由舊到新
    public List<Post> getAllPostTimeAsc() {
        return postRepository.findAllByOrderByCreatedDateAsc();
    }
}


