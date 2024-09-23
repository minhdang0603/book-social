package com.minhdang.identify_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhdang.identify_service.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {}
