package com.minhdang.profile.mapper;

import org.mapstruct.Mapper;

import com.minhdang.profile.dto.request.ProfileCreationRequest;
import com.minhdang.profile.dto.response.UserProfileResponse;
import com.minhdang.profile.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile toUserProfile(ProfileCreationRequest request);

    UserProfileResponse toResponse(UserProfile userProfile);
}
