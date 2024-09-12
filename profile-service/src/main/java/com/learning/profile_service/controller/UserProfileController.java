package com.learning.profile_service.controller;

import java.util.List;

import com.learning.profile_service.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

import com.learning.profile_service.dto.response.UserProfileResponse;
import com.learning.profile_service.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("/{profileId}")
    ApiResponse<UserProfileResponse> getUserProfile(@PathVariable String profileId) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.getProfile(profileId))
                .build();
    }

    @GetMapping("/my-profile")
    ApiResponse<UserProfileResponse> getMyProfile() {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.getMyProfile())
                .build();
    }

    @GetMapping()
    ApiResponse<List<UserProfileResponse>> getAllProfile() {

        return ApiResponse.<List<UserProfileResponse>>builder()
                .result(userProfileService.getAllProfile())
                .build();
    }
}
