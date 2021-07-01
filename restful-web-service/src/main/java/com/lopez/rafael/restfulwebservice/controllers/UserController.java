package com.lopez.rafael.restfulwebservice.controllers;

import com.lopez.rafael.restfulwebservice.models.User;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
    }
}
