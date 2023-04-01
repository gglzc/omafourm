package com.example.Omafourm.controller;

import com.example.Omafourm.entity.Post;
import com.example.Omafourm.entity.User;
import com.example.Omafourm.service.PostService;
import com.example.Omafourm.service.UserService;
import com.example.Omafourm.service.request.LoginRequest;
import com.example.Omafourm.service.request.SignUpRequest;
import com.example.Omafourm.service.request.VerifyRequest;
import com.example.Omafourm.service.response.UpdateResponse;
import com.example.Omafourm.service.response.UserResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @param: UserController
 * @package: com.example.Omafourm.controller.da
 * @className: UserController
 * @description: TODO
 * @return:
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
        boolean userExistOrNot =userService.EmailExistOrNot(signUpRequest.getEmail());

        if (!userExistOrNot) {
            User newUser = userService.SignUp(signUpRequest);
            userService.VerifySignup(newUser);
            return ResponseEntity.ok("User registered successfully. Please verify your email.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed.");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody VerifyRequest verifyRequest) {
        User user = userService.getUserByEmail(verifyRequest.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email.");
        }
        boolean verified = userService.CheckVerifyCode(user, verifyRequest.getCode());
        if (verified) {
            return ResponseEntity.ok("User verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification failed.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        User user = userService.Login(loginRequest.getEmail(),loginRequest.getPassword());
        if (user != null && user.getStatus() == 1) {
            session.setAttribute("user", user);
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or password.");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful.");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UpdateResponse> updateUser(@PathVariable Long id, @RequestBody User user){

        User updateUser = userService.updateUser(user, user.getId());
        List<Post> userPosts = postService.getPostByUser(user);

        UpdateResponse userResponse = new UpdateResponse(updateUser.getId(), updateUser.getUsername(), userPosts);

        return ResponseEntity.ok(userResponse);
    }
    //非公開api 僅能在後台使用
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@RequestParam(value = "id") Long id){
            User user =userService.getUserById(id);
            UserResponse userResponse=new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getPosts(),
                    user.getCreate_time(),
                    user.getLast_login()
            );
            return ResponseEntity.ok(userResponse);
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
            List<User> allUsers =userService.getAllUser();
            List<UserResponse> allUserResponse =allUsers.stream().
                    map(user -> new UserResponse(
                            user.getId(),
                            user.getUsername(),
                            user.getPosts(),
                            user.getCreate_time(),
                            user.getLast_login()
                    ) )
                    .collect(Collectors.toList());
            return ResponseEntity.ok(allUserResponse);
    }


}