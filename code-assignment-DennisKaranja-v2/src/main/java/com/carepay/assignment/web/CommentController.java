package com.carepay.assignment.web;

import com.carepay.assignment.domain.Comment;
import com.carepay.assignment.domain.CommentDetails;
import com.carepay.assignment.domain.CreateCommentRequest;
import com.carepay.assignment.exception.RequestException;
import com.carepay.assignment.service.CommentService;
import com.carepay.assignment.service.CommentServiceImplementor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentServiceImplementor commentServiceImplementor) {
        this.commentService = commentServiceImplementor;
    }

    @GetMapping("/posts/{id}/comments")
    Page<Comment> getComments(@PathVariable("id") final Long id, Pageable pageable) {
        return commentService.getComments(id, pageable);
    }

    @PostMapping("/posts/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    CommentDetails createComment(@PathVariable("id") final Long id, @Valid @RequestBody CreateCommentRequest createCommentRequest) {
        if(createCommentRequest == null){
            throw new RequestException("Empty Request Sent");
        }
        return commentService.createComments(id, createCommentRequest);
    }

    @GetMapping("/posts/{id}/comments/{id}")
    CommentDetails getCommentsDetails(
            @PathVariable("id") final Long postId, @PathVariable("id") final Long commentId) {
        return commentService.getCommentsDetails(postId, commentId);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteComment(@PathVariable("postId") final Long postId, @PathVariable("commentId") final Long commentId) {
        commentService.deleteComment(postId, commentId);
    }
}
