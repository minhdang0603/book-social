package com.minhdang.profile_service.service;

import com.minhdang.profile_service.dto.request.ProfileCreationRequest;
import com.minhdang.profile_service.dto.response.UserProfileResponse;

public interface UserProfileService {
    UserProfileResponse createProfile(ProfileCreationRequest request);

    UserProfileResponse getProfile(String id);
}
