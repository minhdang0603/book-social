package com.minhdang.identity.service;

import java.util.List;

import com.minhdang.identity.dto.request.PermissionRequest;
import com.minhdang.identity.dto.response.PermissionResponse;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> findAll();

    void delete(String permissionName);
}
