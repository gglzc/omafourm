package com.example.Omafourm.service;

import com.example.Omafourm.entity.Comment;
import com.example.Omafourm.entity.Post;
import org.springframework.stereotype.Service;

@Service
public interface CommentService  {
    Comment AddComment(Long postId, Comment comment );
    void DeleteComment(Long commentId);
}
