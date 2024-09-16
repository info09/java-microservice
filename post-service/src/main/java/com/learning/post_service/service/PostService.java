package com.learning.post_service.service;

import java.time.Instant;

import com.learning.post_service.dto.response.UserProfileResponse;
import com.learning.post_service.exception.AppException;
import com.learning.post_service.exception.ErrorCode;
import com.learning.post_service.repository.httpclient.ProfileClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.learning.post_service.dto.PageResponse;
import com.learning.post_service.dto.request.PostRequest;
import com.learning.post_service.dto.response.PostResponse;
import com.learning.post_service.entity.Post;
import com.learning.post_service.mapper.IPostMapper;
import com.learning.post_service.repository.IPostRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    IPostRepository postRepository;
    IPostMapper postMapper;
    DateTimeFormatter dateTimeFormatter;
    ProfileClient profileClient;

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

    public PageResponse<PostResponse> getMyPost(int page, int size) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = authentication.getName();

        UserProfileResponse userProfile = null;

        try {
            userProfile = profileClient.getUserProfile(userId).getResult();
        } catch (Exception e) {
            log.error("Error while getting user profile", e);
        }

        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = postRepository.findByUserId(userId, pageable);

        var userName = userProfile != null ? userProfile.getFirstName()+" "+userProfile.getLastName() : null;
        var postList = pageData.getContent().stream().map(post -> {
            var postResponse = postMapper.toPostDto(post);
            postResponse.setCreated(dateTimeFormatter.format(post.getCreatedDate()));
            postResponse.setUserName(userName);
            return postResponse;
        }).toList();

        return PageResponse.<PostResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPage(pageData.getTotalPages())
                .totalElement(pageData.getTotalElements())
                .data(postList)
                .build();
    }
}
