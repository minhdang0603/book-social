package com.minhdang.profile.service;

import com.minhdang.profile.dto.request.ProfileCreationRequest;
import com.minhdang.profile.dto.response.UserProfileResponse;

import java.util.List;

public interface UserProfileService {
    UserProfileResponse createProfile(ProfileCreationRequest request);

    UserProfileResponse getProfile(String id);

    List<UserProfileResponse> getAllProfiles();
}
