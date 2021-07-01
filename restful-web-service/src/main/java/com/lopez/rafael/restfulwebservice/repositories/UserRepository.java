package com.lopez.rafael.restfulwebservice.repositories;

import com.lopez.rafael.restfulwebservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
