package com.lopez.rafael.restfulwebservice.controllers;

import com.lopez.rafael.restfulwebservice.exceptions.UserNotFoundException;
import com.lopez.rafael.restfulwebservice.models.Post;
import com.lopez.rafael.restfulwebservice.models.User;
import com.lopez.rafael.restfulwebservice.services.PostService;
import com.lopez.rafael.restfulwebservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserService userService;
    private PostService postService;

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        User user = userService.findOne(id);

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkToUsers.withRel("all-users"));
        
        return model;
    }

    @GetMapping("users/{id}/posts")
    public List<Post> retrieveUserPosts(@PathVariable Integer id) {
        User user = userService.findOne(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        return user.getPosts();
    }

    @PostMapping("users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        // We want to return the appropriate HTTP code for resources created => 201
        // WHat's the URI of the new resource created?
        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id, @RequestBody Post post) {
        User user = userService.findOne(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        post.setUser(user);
        Post savedPost = postService.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
    }
}
