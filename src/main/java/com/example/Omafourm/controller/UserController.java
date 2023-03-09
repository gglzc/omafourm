package com.example.Omafourm.controller;

import com.example.Omafourm.entity.User;
import com.example.Omafourm.service.UserService;
import com.example.Omafourm.service.request.LoginRequest;
import com.example.Omafourm.service.request.SignUpRequest;
import com.example.Omafourm.service.request.VerifyRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
        User newUser = userService.SignUp(signUpRequest);
        if (newUser != null) {
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
    public ResponseEntity<String> updateProfile(@PathVariable Long id,@RequestBody User user){

        User updateUser = userService.updateUser(user, user.getId());
        return ResponseEntity.ok("success change profile");
    }
    //非公開api 僅能在crm使用
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<String> getUser(@RequestParam(value = "id") Long id){
            userService.getUserById(id);
            return ResponseEntity.ok("user");
    }
    @GetMapping("/users")
    public ResponseEntity<String> getAllUser(@RequestBody  User user){
            List<User> alluser =userService.getAllUser();
            return ResponseEntity.ok("all users");
    }
}