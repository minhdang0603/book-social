package com.minhdang.profile.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.minhdang.profile.dto.request.ProfileCreationRequest;
import com.minhdang.profile.dto.response.UserProfileResponse;
import com.minhdang.profile.entity.UserProfile;
import com.minhdang.profile.mapper.UserProfileMapper;
import com.minhdang.profile.repository.UserProfileRepository;
import com.minhdang.profile.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
@Slf4j
@Service
public class UserProfileServiceImpl implements UserProfileService {

    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse createProfile(ProfileCreationRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);

        userProfile = userProfileRepository.save(userProfile);

        return userProfileMapper.toResponse(userProfile);
    }

    @Override
    public UserProfileResponse getProfile(String id) {
        UserProfile userProfile =
                userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("User profile not found"));

        return userProfileMapper.toResponse(userProfile);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileResponse> getAllProfiles() {
        var userProfile =
                userProfileRepository.findAll();

        return userProfile.stream().map(userProfileMapper::toResponse).toList();
    }
}
