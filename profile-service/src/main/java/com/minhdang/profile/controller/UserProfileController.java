package com.minhdang.profile.controller;

import com.minhdang.profile.dto.request.SearchUserRequest;
import com.minhdang.profile.dto.request.UpdateProfileRequest;
import com.minhdang.profile.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import com.minhdang.profile.dto.response.UserProfileResponse;
import com.minhdang.profile.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/my-profile")
    public ApiResponse<UserProfileResponse> getMyProfile() {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.getMyProfile())
                .build();
    }

    @PutMapping("/my-profile")
    public ApiResponse<UserProfileResponse> updateMyProfile(@RequestBody UpdateProfileRequest request) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.updateMyProfile(request)) // Replace with actual update logic
                .build();
    }

    @PutMapping("/avatar")
    public ApiResponse<UserProfileResponse> updateAvatar(@RequestParam("file") MultipartFile file) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.updateAvatar(file)) // Replace with actual avatar update logic
                .build();
    }

    @PostMapping("/search")
    public ApiResponse<List<UserProfileResponse>> search(@RequestBody SearchUserRequest request) {
        return ApiResponse.<List<UserProfileResponse>>builder()
                .result(userProfileService.search(request))
                .build();
    }
}
