package com.learning.profile_service.service;

import java.util.List;

import com.learning.profile_service.exception.AppException;
import com.learning.profile_service.exception.ErrorCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public UserProfileResponse getMyProfile() {
        var authenticate = SecurityContextHolder.getContext().getAuthentication();
        var userId = authenticate.getName();

        var userProfile = userProfileRepository.findByUserId(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileResponse> getAllProfile() {
        var listUserProfile = userProfileRepository.findAll();

        return listUserProfile.stream()
                .map(userProfileMapper::toUserProfileResponse)
                .toList();
    }
}
