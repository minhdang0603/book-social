package com.minhdang.identity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.minhdang.identity.dto.request.RoleRequest;
import com.minhdang.identity.dto.response.ApiResponse;
import com.minhdang.identity.dto.response.RoleResponse;
import com.minhdang.identity.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping()
    public ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping()
    public ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.findAll())
                .build();
    }

    @DeleteMapping("/{role}")
    public ApiResponse<Void> delete(@PathVariable("role") String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }
}
