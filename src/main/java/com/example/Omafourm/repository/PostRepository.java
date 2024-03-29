package com.example.Omafourm.repository;

import com.example.Omafourm.entity.Post;
import com.example.Omafourm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);

    Post findPostsById(Long id) ;

}
