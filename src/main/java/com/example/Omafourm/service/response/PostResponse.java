package com.example.Omafourm.service.response;

import com.example.Omafourm.entity.Tag;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

/**
 * @param: PostResponse
 * @package: com.example.Omafourm.service.response
 * @className: PostResponse
 * @description: TODO
 */
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Set<Tag> tag;
    private LocalDateTime createDate;
    private LocalDateTime updateTime;

    public PostResponse(Long id, String title, String content, Set<Tag> tag, LocalDateTime createDate, LocalDateTime updateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.createDate = createDate;
        this.updateTime = updateTime;
    }
    public PostResponse(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
