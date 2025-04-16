package com.minhdang.file.service;

import com.minhdang.file.dto.response.FileData;
import com.minhdang.file.dto.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileResponse uploadMedia(MultipartFile file) throws IOException;

    FileData downloadMedia(String fileName) throws IOException;
}
