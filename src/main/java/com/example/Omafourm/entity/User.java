package com.example.Omafourm.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @param: User
 * @package: com.example.Omafourm.entity
 * @className: User
 * @description: TODO
 * @return:
 */

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    private Long id;
    private String username ;
    private String password ;
    private String email;
    private LocalDateTime   create_time;
    private LocalDateTime   update_time;
    // 0：帳號未被激活 , 1:已激活
    private int    status;
    private String  role;
    private LocalDateTime last_login;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();


    public User(){}


    public User(Long id, String username, String password, String email, LocalDateTime create_time, LocalDateTime update_time, int status, String role, LocalDateTime last_login, List<Post> posts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.create_time = create_time;
        this.update_time = update_time;
        this.status = status;
        this.role = role;
        this.last_login = last_login;
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public LocalDateTime getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(LocalDateTime update_time) {
        this.update_time = update_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public void setLast_login(LocalDateTime last_login) {
        this.last_login = last_login;
    }

    public LocalDateTime getLast_login() {
        return last_login;
    }
}
