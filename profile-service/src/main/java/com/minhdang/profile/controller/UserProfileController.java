package com.minhdang.profile.controller;

import com.minhdang.profile.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import com.minhdang.profile.dto.response.UserProfileResponse;
import com.minhdang.profile.service.UserProfileService;

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
