package com.minhdang.identify_service.service;

import java.util.List;

import com.minhdang.identify_service.dto.request.RoleRequest;
import com.minhdang.identify_service.dto.response.RoleResponse;

public interface RoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> findAll();

    void delete(String roleName);
}
