package com.learning.profile_service.controller;

import com.learning.profile_service.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import com.learning.profile_service.dto.request.ProfileCreateRequest;
import com.learning.profile_service.dto.response.UserProfileResponse;
import com.learning.profile_service.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("internal/users")
public class InternalUserProfileController {
    UserProfileService userProfileService;

    @PostMapping()
    UserProfileResponse createUserProfile(@RequestBody ProfileCreateRequest request) {
        return userProfileService.createProfile(request);
    }

    @GetMapping("/{userId}")
    ApiResponse<UserProfileResponse> getUserProfile(@PathVariable String userId) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.getByUserId(userId))
                .build();
    }
}
