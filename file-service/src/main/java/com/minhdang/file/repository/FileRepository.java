package com.minhdang.file.repository;

import com.minhdang.file.dto.FileInfo;
import com.minhdang.file.entity.FileMgmt;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileRepository {
    FileInfo store(MultipartFile file) throws IOException;

    Resource read(FileMgmt fileMgmt) throws IOException;
}
