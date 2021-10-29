package com.carepay.assignment.service;

import com.carepay.assignment.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface CommentService {
    CommentDetails createComments(Long id, @Valid CreateCommentRequest createCommentRequest);

    Page<Comment> getComments(Long postId, final Pageable pageable);

    CommentDetails getCommentsDetails(Long postId, Long commentId);

    void deleteComment(Long postId, Long commentId);
}
