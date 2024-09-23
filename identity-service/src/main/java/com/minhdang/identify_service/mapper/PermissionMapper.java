package com.minhdang.identify_service.mapper;

import org.mapstruct.Mapper;

import com.minhdang.identify_service.dto.request.PermissionRequest;
import com.minhdang.identify_service.dto.response.PermissionResponse;
import com.minhdang.identify_service.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
