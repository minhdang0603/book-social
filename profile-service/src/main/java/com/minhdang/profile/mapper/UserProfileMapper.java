package com.minhdang.profile.mapper;

import com.minhdang.profile.dto.request.UpdateProfileRequest;
import org.mapstruct.Mapper;

import com.minhdang.profile.dto.request.ProfileCreationRequest;
import com.minhdang.profile.dto.response.UserProfileResponse;
import com.minhdang.profile.entity.UserProfile;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile toUserProfile(ProfileCreationRequest request);

//    @Mapping(source = "avatar", target = "avatar")
    UserProfileResponse toUserProfileResponse(UserProfile userProfile);

    void update(@MappingTarget UserProfile entity, UpdateProfileRequest request);
}
