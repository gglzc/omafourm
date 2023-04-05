package com.example.Omafourm.repository;

import com.example.Omafourm.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment findCommentById(Long id );
}
