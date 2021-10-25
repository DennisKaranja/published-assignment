package com.carepay.assignment.repository;

import com.carepay.assignment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment>findByIdAndPostId(Long Id, Long postId);
}
