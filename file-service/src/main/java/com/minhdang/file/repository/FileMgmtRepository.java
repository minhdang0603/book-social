package com.minhdang.file.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.minhdang.file.entity.FileMgmt;

@Repository
public interface FileMgmtRepository extends MongoRepository<FileMgmt, String> {}
