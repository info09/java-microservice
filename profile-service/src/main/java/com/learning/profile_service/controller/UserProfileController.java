package com.learning.profile_service.controller;

import java.util.List;

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
    UserProfileResponse getUserProfile(@PathVariable String profileId) {
        return userProfileService.getProfile(profileId);
    }

    @GetMapping("/userId/{userId}")
    UserProfileResponse getUserProfileByUserId(@PathVariable String userId) {
        return userProfileService.getProfileByUserId(userId);
    }

    @GetMapping()
    List<UserProfileResponse> getAllProfile() {
        return userProfileService.getAllProfile();
    }
}
