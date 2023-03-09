package com.example.Omafourm.service;

import com.example.Omafourm.entity.User;
import com.example.Omafourm.service.request.SignUpRequest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    /*
    db
    */
    User addUser(User user);
    User getUserById(Long id);
    List<User> getAllUser();
    User updateUser(User user,Long id);
    void deleteUser(Long id);
    Boolean UsernameExistOrNot(String username);
    Boolean EmailExistOrNot(String email);

    /*
    SignUp,
    Login,
    */
    User SignUp(SignUpRequest signUpRequest);
    User Login(String email , String password);

    User getUserByEmail(String email);
    boolean CheckVerifyCode(User user, String Code);
    void VerifySignup(User user);
}
