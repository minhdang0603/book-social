package com.minhdang.profile.controller;

import com.minhdang.profile.dto.request.ProfileCreationRequest;
import com.minhdang.profile.dto.response.UserProfileResponse;
import com.minhdang.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/internal/users")
public class InternalUserProfileController {

    UserProfileService userProfileService;

    @PostMapping()
    public UserProfileResponse createUserProfile(@RequestBody ProfileCreationRequest request) {
        return userProfileService.createProfile(request);
    }
}
