package com.minhdang.profile.service;

import com.minhdang.profile.dto.request.ProfileCreationRequest;
import com.minhdang.profile.dto.request.UpdateProfileRequest;
import com.minhdang.profile.dto.response.UserProfileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserProfileService {
    UserProfileResponse createProfile(ProfileCreationRequest request);

    UserProfileResponse getByUserId(String userId);

    UserProfileResponse getProfile(String id);

    List<UserProfileResponse> getAllProfiles();
    UserProfileResponse getMyProfile();
    UserProfileResponse updateMyProfile(UpdateProfileRequest request);
    UserProfileResponse updateAvatar(MultipartFile file);
}
