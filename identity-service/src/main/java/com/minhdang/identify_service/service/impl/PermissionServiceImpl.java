package com.minhdang.identify_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.minhdang.identify_service.dto.request.PermissionRequest;
import com.minhdang.identify_service.dto.response.PermissionResponse;
import com.minhdang.identify_service.entity.Permission;
import com.minhdang.identify_service.mapper.PermissionMapper;
import com.minhdang.identify_service.repository.PermissionRepository;
import com.minhdang.identify_service.service.PermissionService;

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
