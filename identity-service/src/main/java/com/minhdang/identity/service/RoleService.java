package com.minhdang.identity.service;

import java.util.List;

import com.minhdang.identity.dto.request.RoleRequest;
import com.minhdang.identity.dto.response.RoleResponse;

public interface RoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> findAll();

    void delete(String roleName);
}
