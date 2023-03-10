package com.example.Omafourm.service.impl;

import com.example.Omafourm.entity.User;
import com.example.Omafourm.service.MailService;
import com.example.Omafourm.service.UserService;
import com.example.Omafourm.service.VerificationService;
import com.example.Omafourm.service.request.SignUpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Omafourm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;


/**
 * @param: UserServiceImpl
 * @package: com.example.Omafourm.service.impl
 * @className: UserServiceImpl
 * @description: 會員申請登入處理
 * @return:
 */
@Service
public class UserServiceImpl implements UserService {
    private  static  final Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;


    @Qualifier("verificationServiceImpl")
    @Autowired
    private VerificationService verifyService;


    public UserServiceImpl( UserRepository userRepository,BCryptPasswordEncoder passwordEncoder){
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->
                    new MissingResourceException("can't find User","user","id"));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user, Long id) {
        User existUser = getUserById(id);
        existUser.setUsername(user.getUsername());
        existUser.setPassword(user.getPassword());
        existUser.setEmail(user.getEmail());
        existUser.setStatus(user.getStatus());
        return userRepository.save(existUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Boolean UsernameExistOrNot(String username) {
        User user = userRepository.findByUsername(username);
        return user != null && username.equals(user.getUsername());
    }

    @Override
    public Boolean EmailExistOrNot(String email) {
        User user = userRepository.findByEmail(email);
        return user != null && email.equals(user.getEmail());
    }
    @Override
    public User getUserByEmail(String email) {
        User user =userRepository.getUserByEmail(email);
        return user;
    }

    @Override
    public User SignUp(SignUpRequest signUpRequest) {
        //Username 及email 是否存在
        User user = new User();
        try {
            if (EmailExistOrNot(signUpRequest.getEmail())) {
                logger.error("Email is already Exist!!");
                return null;
            }
            if (UsernameExistOrNot(signUpRequest.getUsername())) {
                logger.error("User is already Exist!!");
                return null;
            }
            //先把密碼加密
            String encodePassword = passwordEncoder.encode(signUpRequest.getPassword());


            user.setUsername(signUpRequest.getUsername());
            user.setPassword(encodePassword);
            user.setEmail(signUpRequest.getEmail());
            user.setStatus(0);
            user.setRole("user");
            Date now = new Date();
            user.setCreate_time(now);
            user.setUpdate_time(now);

            addUser(user);

            logger.info("Someone has Signup: ", user.getUsername());
        } catch (Exception e) {
            logger.error("Something Wrong with SignUp",e);
            return null;
        }
        return user;
    }
    public void VerifySignup(User user){

        //寄驗證信箱
        String verify=verifyService.generateVerificationCode(user.getEmail());
        mailService.sendMail(
                user.getEmail(),
                "OmaFourm Register Check Mail",
                "你的驗證碼為： "+ verify
        );
    }

    public boolean CheckVerifyCode(User user,String Code){
        boolean verified=verifyService.verifyCode(Code);
        if (verified){
            user.setStatus(1);
            updateUser(user,user.getId()).setStatus(1);
            return true;
        }
        return false;
    }
    @Override
    public User Login(String email , String password) {
        User user=userRepository.getUserByEmail(email);

        try{
           if (user!=null &&  passwordEncoder.matches(password,user.getPassword())){
               //更新上線時間
               user.setLast_login(LocalDateTime.now());
               userRepository.save(user);
               logger.info("User has logged in: " + user.getUsername());
           }else{
               logger.error("Login failed for email or password: " + email);
           }
        }catch(Exception e){
            logger.error("Error occurred while trying to login: " + e.getMessage());
        }
        return user;
    }






}
