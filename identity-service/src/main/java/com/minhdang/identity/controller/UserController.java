package com.minhdang.identity.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.minhdang.identity.dto.request.UserCreationRequest;
import com.minhdang.identity.dto.request.UserUpdateRequest;
import com.minhdang.identity.dto.response.ApiResponse;
import com.minhdang.identity.dto.response.UserResponse;
import com.minhdang.identity.service.UserService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @PostMapping("/registration")
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {

        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.createUser(request));

        return response;
    }

    @GetMapping()
    public ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUserById(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(userId))
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(request, userId))
                .build();
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<String>builder().result("User deleted").build();
    }

    @GetMapping("/my-info")
    public ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }
}
