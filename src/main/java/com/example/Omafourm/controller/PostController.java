package com.example.Omafourm.controller;

import com.example.Omafourm.entity.Post;
import com.example.Omafourm.entity.User;
import com.example.Omafourm.repository.PostRepository;
import com.example.Omafourm.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @param: PostController
 * @package: com.example.Omafourm.controller
 * @className: PostController
 * @description: TODO
 * @return:
 */
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/")
    public ResponseEntity<String> userAddPost(){
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> userDeletePost( User user , Post post){
        return ResponseEntity.ok("Delete success");
    }
}
