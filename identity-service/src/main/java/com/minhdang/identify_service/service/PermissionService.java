package com.minhdang.identify_service.service;

import java.util.List;

import com.minhdang.identify_service.dto.request.PermissionRequest;
import com.minhdang.identify_service.dto.response.PermissionResponse;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> findAll();

    void delete(String permissionName);
}
