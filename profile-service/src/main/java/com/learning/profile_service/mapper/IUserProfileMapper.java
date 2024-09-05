package com.learning.profile_service.mapper;

import com.learning.profile_service.dto.request.ProfileCreateRequest;
import com.learning.profile_service.dto.response.UserProfileResponse;
import com.learning.profile_service.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserProfileMapper {
    UserProfile toUserProfile(ProfileCreateRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile user);
}
