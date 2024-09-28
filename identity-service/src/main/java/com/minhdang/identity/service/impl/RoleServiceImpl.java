package com.minhdang.identity.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.minhdang.identity.dto.request.RoleRequest;
import com.minhdang.identity.dto.response.RoleResponse;
import com.minhdang.identity.entity.Role;
import com.minhdang.identity.mapper.RoleMapper;
import com.minhdang.identity.repository.PermissionRepository;
import com.minhdang.identity.repository.RoleRepository;
import com.minhdang.identity.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(String roleName) {
        roleRepository.deleteById(roleName);
    }
}
