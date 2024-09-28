package com.minhdang.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.minhdang.identity.dto.request.RoleRequest;
import com.minhdang.identity.dto.response.RoleResponse;
import com.minhdang.identity.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
