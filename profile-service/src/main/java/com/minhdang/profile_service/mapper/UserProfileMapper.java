package com.minhdang.profile_service.mapper;

import org.mapstruct.Mapper;

import com.minhdang.profile_service.dto.request.ProfileCreationRequest;
import com.minhdang.profile_service.dto.response.UserProfileResponse;
import com.minhdang.profile_service.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile toUserProfile(ProfileCreationRequest request);

    UserProfileResponse toResponse(UserProfile userProfile);
}
