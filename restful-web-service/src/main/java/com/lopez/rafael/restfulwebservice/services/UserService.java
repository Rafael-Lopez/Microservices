package com.lopez.rafael.restfulwebservice.services;

import com.lopez.rafael.restfulwebservice.exceptions.UserNotFoundException;
import com.lopez.rafael.restfulwebservice.models.User;
import com.lopez.rafael.restfulwebservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findOne(int id) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        return user;
    }

    public User deleteById(int id) {
        User user = findOne(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        userRepository.deleteById(id);

        return user;
    }
}
