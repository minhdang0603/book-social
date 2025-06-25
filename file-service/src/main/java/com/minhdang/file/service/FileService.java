package com.minhdang.file.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.minhdang.file.dto.response.FileData;
import com.minhdang.file.dto.response.FileResponse;

public interface FileService {
    FileResponse uploadMedia(MultipartFile file) throws IOException;

    FileData downloadMedia(String fileName) throws IOException;
}
