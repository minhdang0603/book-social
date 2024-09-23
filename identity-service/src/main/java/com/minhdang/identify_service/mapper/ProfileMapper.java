package com.minhdang.identify_service.mapper;

import org.mapstruct.Mapper;

import com.minhdang.identify_service.dto.request.ProfileCreationRequest;
import com.minhdang.identify_service.dto.request.UserCreationRequest;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
