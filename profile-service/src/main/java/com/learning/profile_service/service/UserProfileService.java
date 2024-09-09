package com.learning.profile_service.service;

import org.springframework.stereotype.Service;

import com.learning.profile_service.dto.request.ProfileCreateRequest;
import com.learning.profile_service.dto.response.UserProfileResponse;
import com.learning.profile_service.mapper.IUserProfileMapper;
import com.learning.profile_service.repository.IUserProfileRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileService {
    IUserProfileRepository userProfileRepository;
    IUserProfileMapper userProfileMapper;

    public UserProfileResponse createProfile(ProfileCreateRequest request) {
        var userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);

        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse getProfile(String id) {
        var userProfile =
                userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));

        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse getProfileByUserId(String userId){
        var userProfile = userProfileRepository.findByUserId(userId);

        return userProfileMapper.toUserProfileResponse(userProfile);
    }
}
