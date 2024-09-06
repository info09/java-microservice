package com.learning.identity_server.mapper;

import org.mapstruct.Mapper;

import com.learning.identity_server.dto.request.ProfileCreateRequest;
import com.learning.identity_server.dto.request.UserCreateRequest;

@Mapper(componentModel = "spring")
public interface IProfileMapper {
    ProfileCreateRequest toProfileCreateRequest(UserCreateRequest request);
}
