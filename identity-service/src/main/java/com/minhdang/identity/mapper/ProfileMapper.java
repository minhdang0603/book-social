package com.minhdang.identity.mapper;

import org.mapstruct.Mapper;

import com.minhdang.identity.dto.request.ProfileCreationRequest;
import com.minhdang.identity.dto.request.UserCreationRequest;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
