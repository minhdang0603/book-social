package com.minhdang.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhdang.identity.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {}
