package com.minhdang.identify_service.service;

import java.util.List;

import com.minhdang.identify_service.dto.request.UserCreationRequest;
import com.minhdang.identify_service.dto.request.UserUpdateRequest;
import com.minhdang.identify_service.dto.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getUsers();

    UserResponse getUserById(String id);

    UserResponse updateUser(UserUpdateRequest request, String userId);

    void deleteUser(String userId);

    UserResponse getMyInfo();
}
