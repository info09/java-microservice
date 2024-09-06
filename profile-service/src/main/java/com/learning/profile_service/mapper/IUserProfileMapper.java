package com.learning.profile_service.mapper;

import org.mapstruct.Mapper;

import com.learning.profile_service.dto.request.ProfileCreateRequest;
import com.learning.profile_service.dto.response.UserProfileResponse;
import com.learning.profile_service.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface IUserProfileMapper {
    UserProfile toUserProfile(ProfileCreateRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile user);
}
