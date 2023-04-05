package com.example.Omafourm.service;

import com.example.Omafourm.entity.Comment;
import com.example.Omafourm.entity.Post;
import org.springframework.stereotype.Service;

@Service
public interface CommentService  {
    Comment AddComment(Post post);
    void DeleteComment(Post post);
}
