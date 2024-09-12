package com.learning.post_service.service;

import java.time.Instant;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.learning.post_service.dto.request.PostRequest;
import com.learning.post_service.dto.response.PostResponse;
import com.learning.post_service.entity.Post;
import com.learning.post_service.mapper.IPostMapper;
import com.learning.post_service.repository.IPostRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    IPostRepository postRepository;
    IPostMapper postMapper;

    public PostResponse createPost(PostRequest request) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var post = Post.builder()
                .userId(authentication.getName())
                .content(request.getContent())
                .createdDate(Instant.now())
                .modifiedDate(Instant.now())
                .build();

        post = postRepository.save(post);
        return postMapper.toPostDto(post);
    }

    public List<PostResponse> getMyPost(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return postRepository.findByUserId(authentication.getName()).stream()
                .map(postMapper::toPostDto)
                .toList();
    }
}
