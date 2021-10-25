package com.carepay.assignment.service;

import com.carepay.assignment.domain.CreatePostRequest;
import com.carepay.assignment.domain.Post;
import com.carepay.assignment.domain.PostDetails;
import com.carepay.assignment.domain.PostInfo;
import com.carepay.assignment.repository.PostInfoRepository;
import com.carepay.assignment.repository.PostRepository;
import com.carepay.assignment.web.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostInfoRepository postInfoRepository;

    public PostServiceImpl(PostRepository postRepository,
                           PostInfoRepository postInfoRepository) {
        this.postRepository = postRepository;
        this.postInfoRepository = postInfoRepository;
    }

    @Override
    public PostDetails createPost(@Valid CreatePostRequest createPostRequest) {

        if((createPostRequest.getTitle()==null) || (createPostRequest.getContent()==null))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
        else
        {
            Post post = new Post();
            post.setTitle(createPostRequest.getTitle());
            post.setContent(createPostRequest.getContent());

            if(createPostRequest.getTitle().length() > 20)
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            Post savedPost = postRepository.save(post);

            PostDetails postDetails = new PostDetails();
            postDetails.setId(savedPost.getId());
            postDetails.setTitle(savedPost.getTitle());
            postDetails.setContent(savedPost.getContent());

            return postDetails;
        }
    }

    @Override
    public Page<PostInfo> getPosts(Pageable pageable) {
        return postInfoRepository.findAll(pageable);
    }

    @Override
    public PostDetails getPostDetails(Long id) {

        Optional<Post> savedPosts = postRepository.findById(id);
        PostDetails postDetails = new PostDetails();

        if(savedPosts.isPresent())
        {
            postDetails.setTitle(savedPosts.get().getTitle());
            postDetails.setId(savedPosts.get().getId());
            postDetails.setContent(savedPosts.get().getContent());
            return postDetails;
            //throw new ResponseStatusException(HttpStatus.OK);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }


    }

    @Override
    public void deletePost(Long id) {

        Optional<Post> savedPosts = postRepository.findById(id);
        if(!savedPosts.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (savedPosts.isPresent())
        {
            postRepository.findById(id).map(post -> {
                postRepository.delete(post);
                return new ResponseStatusException(HttpStatus.OK);
            });
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
