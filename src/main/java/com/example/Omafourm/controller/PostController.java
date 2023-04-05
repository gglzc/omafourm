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
        try {
            Post post = postService.getPost(id);
            PostResponse postResponse = new PostResponse();

            postResponse.setId(post.getId());
            postResponse.setTitle(post.getTitle());
            postResponse.setContent(post.getContent());
            postResponse.setTag(post.getTags());
            postResponse.setCreateDate(post.getCreatedDate());
            postResponse.setUpdateTime(post.getUpdateDate());

            return ResponseEntity.ok(postResponse);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/post")
    public ResponseEntity<PostResponse> userAddPost(@RequestBody PostRequest postRequest , HttpSession session){
        //要登入才能發表文章
        if (session.getAttribute("id")==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Long userId = (Long) session.getAttribute("id"); // 從 session 中取得用戶 ID
        User user = userService.getUserById(userId); // 根據用戶 ID 從資料庫中取得用戶對象
        if (user == null) { // 如果用戶不存在，返回 401 錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Post newPost = postService.createPost(
                user,
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
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> userDeletePost(@PathVariable Long id, HttpSession session){
        // 驗證用戶是否已登入
        Long userId = (Long) session.getAttribute("id");
        if (userId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("您必須先登入");
        }
        try {
            postService.deletePost(id ,userId);
            return ResponseEntity.ok("Delete success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("刪除失敗，請稍後再試");
        }
    }

    @PatchMapping ("/post/{id}")
    public ResponseEntity<PostResponse>userUpdatePost (@PathVariable Long id ,@RequestBody  PostRequest postRequest ,HttpSession session){
        Long userId = (Long) session.getAttribute("id");
        if (userId == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Post post = postService.getPost(id);
        User user =post.getUser();

        Post updatePost =postService.updatePost(
                user ,
                post ,
                postRequest.getTitle(),
                postRequest.getContent(),
                postRequest.getTag()
        );
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
