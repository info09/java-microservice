package com.learning.profile_service.controller;

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
}
