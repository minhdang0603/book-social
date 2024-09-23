package com.minhdang.identify_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhdang.identify_service.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
