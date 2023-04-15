package com.example.Omafourm.service.impl;

import com.example.Omafourm.entity.Post;
import com.example.Omafourm.repository.TagRepository;
import com.example.Omafourm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @param: TagServiceImpl
 * @package: com.example.Omafourm.service.impl
 * @className: TagServiceImpl
 * @description: Tag Service

 */
public class TagServiceImpl  implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Override
    public List<Post> getPostsByTagName(String tagName) {
        List <Post> posts=tagRepository.findPostsByTag(tagName);
        return posts;
    }
}
