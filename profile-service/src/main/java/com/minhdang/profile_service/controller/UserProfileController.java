package com.minhdang.profile_service.controller;

import org.springframework.web.bind.annotation.*;

import com.minhdang.profile_service.dto.request.ProfileCreationRequest;
import com.minhdang.profile_service.dto.response.UserProfileResponse;
import com.minhdang.profile_service.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserProfileController {

    UserProfileService userProfileService;

    @PostMapping()
    public UserProfileResponse createUserProfile(@RequestBody ProfileCreationRequest request) {
        return userProfileService.createProfile(request);
    }

    @GetMapping("/{profileId}")
    public UserProfileResponse getUserProfile(@PathVariable String profileId) {
        return userProfileService.getProfile(profileId);
    }
}
