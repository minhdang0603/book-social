package com.minhdang.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhdang.identity.entity.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {}
