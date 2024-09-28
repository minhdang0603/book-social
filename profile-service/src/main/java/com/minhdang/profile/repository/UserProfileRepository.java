package com.minhdang.profile.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.minhdang.profile.entity.UserProfile;

@Repository
public interface UserProfileRepository extends Neo4jRepository<UserProfile, String> {}
