package com.learning.post_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learning.post_service.entity.Post;

public interface IPostRepository extends MongoRepository<Post, String> {}
