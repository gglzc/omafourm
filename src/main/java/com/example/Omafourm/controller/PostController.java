package com.example.Omafourm.controller;

import com.example.Omafourm.entity.Post;
import com.example.Omafourm.entity.User;
import com.example.Omafourm.repository.PostRepository;
import com.example.Omafourm.service.PostService;
import com.example.Omafourm.service.UserService;
import com.example.Omafourm.service.request.PostRequest;
import com.example.Omafourm.service.response.PostResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @param: PostController
 * @package: com.example.Omafourm.controller
 * @className: PostController
 * @description: TODO
 * @return:
 */
@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @GetMapping("/post/{id}")
    public ResponseEntity<PostResponse> getPost (@PathVariable Long id){
        Post post =postService.getPost(id);
        PostResponse postResponse= new PostResponse();

        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setCreateDate(post.getCreatedDate());
        postResponse.setUpdateTime(post.getUpdateDate());

        return ResponseEntity.ok(postResponse);
    }
    @PostMapping("/post")
    public ResponseEntity<PostResponse> userAddPost(@RequestBody PostRequest postRequest , HttpSession session){
        //要登入才能發表文章
        if (session.getAttribute("id")==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Post newPost = postService.createPost(
                postRequest.getTitle(),
                postRequest.getContent(),
                postRequest.getTag()
        );
        PostResponse response = new PostResponse(
                newPost.getId(),
                newPost.getTitle(),
                newPost.getTitle(),
                newPost.getTags(),
                newPost.getCreatedDate(),
                newPost.getUpdateDate()
        );
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> userDeletePost( User user , Post post , HttpSession session){
        if (session.getAttribute("id")==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have to login first");
        }
        postService.deletePost(user,post);
        return ResponseEntity.ok("Delete success");
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostResponse>userUpdatePost (@PathVariable Long id ,@RequestBody  PostRequest postRequest ,HttpSession session){
        if (session.getAttribute("id")==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = userService.getUserById(postRequest.getUser_id());
        Post post = postService.getPost(id);

        Post updatePost =postService.updatePost(user ,
                post ,
                postRequest.getTitle(),
                postRequest.getContent(),
                postRequest.getTag()
        );
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
