package com.example.Omafourm.service;

import com.example.Omafourm.entity.Post;

import java.util.List;

public interface TagService {
    List<Post> getPostsByTagName(String tagName);
}
