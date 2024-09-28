package com.minhdang.identity.mapper;

import org.mapstruct.Mapper;

import com.minhdang.identity.dto.request.PermissionRequest;
import com.minhdang.identity.dto.response.PermissionResponse;
import com.minhdang.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
