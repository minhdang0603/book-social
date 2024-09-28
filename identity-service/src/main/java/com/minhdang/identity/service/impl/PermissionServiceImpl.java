package com.minhdang.identity.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.minhdang.identity.dto.request.PermissionRequest;
import com.minhdang.identity.dto.response.PermissionResponse;
import com.minhdang.identity.entity.Permission;
import com.minhdang.identity.mapper.PermissionMapper;
import com.minhdang.identity.repository.PermissionRepository;
import com.minhdang.identity.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class PermissionServiceImpl implements PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public List<PermissionResponse> findAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(String permissionName) {
        permissionRepository.deleteById(permissionName);
    }
}
