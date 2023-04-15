package com.example.Omafourm.controller;

import com.example.Omafourm.service.CommentService;
import com.example.Omafourm.service.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @param: CommentController
 * @package: com.example.Omafourm.controller
 * @className: CommentController
 * @description: Comment API Controller
 */
@Controller
@RequestMapping("/api")
public class CommentController {
    /**
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<String> addComment(CommentRequest commentRequest){

    }
    **/
}
