package com.learning.identity_server.repository.httpclient;

import com.learning.identity_server.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.learning.identity_server.dto.request.ProfileCreateRequest;
import com.learning.identity_server.dto.response.UserProfileResponse;

@FeignClient(name = "profile-service", url = "${app.service.profile}")
public interface ProfileClient {
    @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<UserProfileResponse> createProfile(@RequestBody ProfileCreateRequest request);
}
