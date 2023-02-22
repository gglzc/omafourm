package com.example.Omafourm.repository;

import com.example.Omafourm.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
