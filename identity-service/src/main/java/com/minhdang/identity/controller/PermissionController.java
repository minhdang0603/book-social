package com.minhdang.identity.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.minhdang.identity.dto.request.PermissionRequest;
import com.minhdang.identity.dto.response.ApiResponse;
import com.minhdang.identity.dto.response.PermissionResponse;
import com.minhdang.identity.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {

    PermissionService permissionService;

    @PostMapping()
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest permissionRequest) {
        PermissionResponse permissionResponse = permissionService.create(permissionRequest);

        return ApiResponse.<PermissionResponse>builder()
                .result(permissionResponse)
                .build();
    }

    @GetMapping()
    ApiResponse<List<PermissionResponse>> findAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.findAll())
                .build();
    }

    @DeleteMapping("/{permissionId}")
    ApiResponse<Void> delete(@PathVariable String permissionId) {
        permissionService.delete(permissionId);
        return ApiResponse.<Void>builder().build();
    }
}
