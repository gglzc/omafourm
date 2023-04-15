package com.example.Omafourm.service.impl;

import com.example.Omafourm.entity.Comment;
import com.example.Omafourm.entity.Post;
import com.example.Omafourm.repository.CommentRepository;
import com.example.Omafourm.repository.PostRepository;
import com.example.Omafourm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

/**
 * @param: CommentServiceImpl
 * @package: com.example.Omafourm.service.impl
 * @className: CommentServiceImpl
 * @description: 文章留言
 */
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Transactional
    @Override
    public Comment AddComment(Long postId, Comment comment) {
        Post post = postRepository.findPostsById(postId);
        comment.setPost(post);
        return commentRepository.save(comment);
    }
    @Transactional
    @Override
    public void DeleteComment(Long commentId) {
        Comment comment =commentRepository.findCommentById(commentId);
        commentRepository.delete(comment);
    }
}
