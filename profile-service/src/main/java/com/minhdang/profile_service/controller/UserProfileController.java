package com.minhdang.profile_service.controller;

import com.minhdang.profile_service.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import com.minhdang.profile_service.dto.request.ProfileCreationRequest;
import com.minhdang.profile_service.dto.response.UserProfileResponse;
import com.minhdang.profile_service.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserProfileController {

    UserProfileService userProfileService;

    @GetMapping("/{profileId}")
    public ApiResponse<UserProfileResponse> getUserProfile(@PathVariable String profileId) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.getProfile(profileId))
                .build();
    }

    @GetMapping()
    public ApiResponse<List<UserProfileResponse>> getAllProfile() {
        return ApiResponse.<List<UserProfileResponse>>builder()
                .result(userProfileService.getAllProfiles())
                .build();
    }

}
