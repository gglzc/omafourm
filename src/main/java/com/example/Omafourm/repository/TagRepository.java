package com.example.Omafourm.repository;

import com.example.Omafourm.entity.Post;
import com.example.Omafourm.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    List<Post> findPostsByTag(String tagName);
}
