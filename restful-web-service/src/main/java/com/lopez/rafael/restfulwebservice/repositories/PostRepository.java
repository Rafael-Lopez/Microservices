package com.lopez.rafael.restfulwebservice.repositories;

import com.lopez.rafael.restfulwebservice.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
