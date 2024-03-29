package com.example.Omafourm.service.request;

import com.example.Omafourm.entity.Tag;

import java.util.List;
import java.util.Set;

/**
 * @param: PostRequest
 * @package: com.example.Omafourm.service.request
 * @className: PostRequest
 * @description: TODO
 */
public class PostRequest {
    private String title;
    private String content;
    private Set<Tag> tag;
    private Long user_id;

    public PostRequest(String title, String content, Set<Tag> tag) {
        this.title = title;
        this.content = content;
        this.tag = tag;
    }

    public PostRequest(String title, String content, Set<Tag> tag, Long user_id) {
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Tag> getTag() {
        return tag;
    }

    public void setTag(Set<Tag> tag) {
        this.tag = tag;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
