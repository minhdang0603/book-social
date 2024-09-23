package com.minhdang.identify_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhdang.identify_service.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
