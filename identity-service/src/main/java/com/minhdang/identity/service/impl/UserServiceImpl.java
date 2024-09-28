package com.minhdang.identity.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.minhdang.event.dto.NotificationEvent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.minhdang.identity.constrant.PredefinedRole;
import com.minhdang.identity.dto.request.UserCreationRequest;
import com.minhdang.identity.dto.request.UserUpdateRequest;
import com.minhdang.identity.dto.response.UserResponse;
import com.minhdang.identity.entity.Role;
import com.minhdang.identity.entity.User;
import com.minhdang.identity.exception.AppException;
import com.minhdang.identity.exception.ErrorCode;
import com.minhdang.identity.mapper.ProfileMapper;
import com.minhdang.identity.mapper.UserMapper;
import com.minhdang.identity.repository.RoleRepository;
import com.minhdang.identity.repository.UserRepository;
import com.minhdang.identity.repository.httpclient.ProfileClient;
import com.minhdang.identity.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    ProfileClient profileClient;
    ProfileMapper profileMapper;
    KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public UserResponse createUser(UserCreationRequest request) {

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);
        user.setRoles(roles);
        try {
            user = userRepository.save(user);

            var profileRequest = profileMapper.toProfileCreationRequest(request);

            profileRequest.setUserId(user.getId());

            profileClient.createProfile(profileRequest);

            NotificationEvent notificationEvent = NotificationEvent.builder()
                    .channel("EMAIL")
                    .recipient(request.getEmail())
                    .subject("Welcome to book social")
                    .body("Hello, " + request.getUsername())
                    .build();

            // Public message to kafka
            kafkaTemplate.send("notification-delivery", notificationEvent);

            return userMapper.toUserResponse(user);

        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse updateUser(UserUpdateRequest request, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());

        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();

        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
}
