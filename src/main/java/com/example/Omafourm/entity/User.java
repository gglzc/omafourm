package com.example.Omafourm.entity;

import jakarta.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;
    private String username ;
    private String password ;
    private String email;
    private Date   create_time;
    private Date   update_time;
    // 0：帳號未被激活 , 1:已激活
    private int    status;
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public User(long id, String username, String password, String email, Date create_time, Date update_time, int status,List<Post> posts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.create_time = create_time;
        this.update_time = update_time;
        this.status = status;
        this.posts=posts;
    }
    public User(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
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
}
