package com.learning.post_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learning.post_service.entity.Post;

import java.util.List;

public interface IPostRepository extends MongoRepository<Post, String> {
    List<Post> findByUserId(String userId);
}
