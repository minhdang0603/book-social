package com.minhdang.identity.service;

import java.util.List;

import com.minhdang.identity.dto.request.UserCreationRequest;
import com.minhdang.identity.dto.request.UserUpdateRequest;
import com.minhdang.identity.dto.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserCreationRequest request);

    List<UserResponse> getUsers();

    UserResponse getUserById(String id);

    UserResponse updateUser(UserUpdateRequest request, String userId);

    void deleteUser(String userId);

    UserResponse getMyInfo();
}
