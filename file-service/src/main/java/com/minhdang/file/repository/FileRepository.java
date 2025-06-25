package com.minhdang.file.repository;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.minhdang.file.dto.FileInfo;
import com.minhdang.file.entity.FileMgmt;

public interface FileRepository {
    FileInfo store(MultipartFile file) throws IOException;

    Resource read(FileMgmt fileMgmt) throws IOException;
}
