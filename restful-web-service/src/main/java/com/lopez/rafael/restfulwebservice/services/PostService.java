package com.lopez.rafael.restfulwebservice.services;

import com.lopez.rafael.restfulwebservice.models.Post;
import com.lopez.rafael.restfulwebservice.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }
}
