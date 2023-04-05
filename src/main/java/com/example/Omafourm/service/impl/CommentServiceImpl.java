package com.example.Omafourm.service.impl;

import com.example.Omafourm.entity.Comment;
import com.example.Omafourm.entity.Post;
import com.example.Omafourm.repository.CommentRepository;
import com.example.Omafourm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * @param: CommentServiceImpl
 * @package: com.example.Omafourm.service.impl
 * @className: CommentServiceImpl
 * @description: 文章留言
 */
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Override
    Comment AddComment(Post post, Comment comment ){
        //先檢查發文的使用者是不是同個
        if (!post.getUser().equals(comment.getPost().getUser().getId()){

        }


    }

    @Override
    void DeleteComment(Long commentId, Long userId){

    }
}
