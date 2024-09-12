package com.learning.post_service.service;

import org.springframework.stereotype.Service;

import com.learning.post_service.repository.IPostRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    IPostRepository postRepository;
}
