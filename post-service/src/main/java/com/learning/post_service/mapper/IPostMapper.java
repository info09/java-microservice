package com.learning.post_service.mapper;

import org.mapstruct.Mapper;

import com.learning.post_service.dto.request.PostRequest;
import com.learning.post_service.dto.response.PostResponse;
import com.learning.post_service.entity.Post;

@Mapper(componentModel = "spring")
public interface IPostMapper {
    Post toPost(PostRequest request);

    PostResponse toPostDto(Post post);
}
