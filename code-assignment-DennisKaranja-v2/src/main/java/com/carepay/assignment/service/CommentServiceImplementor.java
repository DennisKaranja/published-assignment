package com.carepay.assignment.service;

import com.carepay.assignment.domain.Comment;
import com.carepay.assignment.domain.CommentDetails;
import com.carepay.assignment.domain.CreateCommentRequest;
import com.carepay.assignment.exception.RequestException;
import com.carepay.assignment.repository.CommentRepository;
import com.carepay.assignment.repository.PostRepository;
import com.carepay.assignment.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;


@Service
public class CommentServiceImplementor implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public CommentServiceImplementor(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Page<Comment> getComments(Long id, Pageable pageable) {
        return postRepository.findById(id).map(post -> {
            return commentRepository.findAll(pageable);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
    }

    @Override
    public CommentDetails createComments(Long postId, @Valid CreateCommentRequest createCommentRequest) {

        if(createCommentRequest.getComment().length() > 50)
        {
            throw new RequestException("Comment Too Long");
        }
        else
        {
        Comment postComments = new Comment();
        postComments.setPostId(createCommentRequest.getPostId());
        postComments.setComment(createCommentRequest.getComment());

        Comment savedComment = commentRepository.save(postComments);

        System.out.print("Post Id!!!" + createCommentRequest.getPostId());
        System.out.print("Comment!!!" + createCommentRequest.getComment());

        CommentDetails commentDetails = new CommentDetails();
        commentDetails.setComment(savedComment.getComment());
        commentDetails.setId(savedComment.getId());
        commentDetails.setPostId(savedComment.getPostId());

        return commentDetails;
    }
    }

    @Override
    public CommentDetails getCommentsDetails(Long postId, Long commentId) {

        CommentDetails commentDetails = new CommentDetails();
        Optional<Comment> savedComments = commentRepository.findById(commentId);

        if(savedComments.isPresent())
        {
            commentDetails.setId(savedComments.get().getId());
            commentDetails.setPostId(savedComments.get().getPostId());
            commentDetails.setComment(savedComments.get().getComment());

            return commentDetails;
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Optional<Comment> savedComments = commentRepository.findByIdAndPostId(commentId, postId);

        if ((postId == null) || (commentId == null)) {
            throw new ResourceNotFoundException("Error with CommentId "+commentId+" and PostId "+postId);
        }

        if (savedComments.isPresent()) {
            commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
                commentRepository.delete(comment);
                return new ResponseStatusException(HttpStatus.OK);
            });
        }
        else
        {
            throw new ResourceNotFoundException("Comment not found with id "+commentId+" and PostId "+postId);
        }
    }
}
