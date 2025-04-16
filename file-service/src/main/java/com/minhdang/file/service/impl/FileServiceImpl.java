package com.minhdang.file.service.impl;

import com.minhdang.file.dto.response.FileData;
import com.minhdang.file.dto.response.FileResponse;
import com.minhdang.file.exception.AppException;
import com.minhdang.file.exception.ErrorCode;
import com.minhdang.file.mapper.FileMgmtMapper;
import com.minhdang.file.repository.FileMgmtRepository;
import com.minhdang.file.repository.FileRepository;
import com.minhdang.file.service.FileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileServiceImpl implements FileService {

    FileRepository fileRepository;
    FileMgmtRepository fileMgmtRepository;
    FileMgmtMapper fileMgmtMapper;

    @Override
    public FileResponse uploadMedia(MultipartFile file) throws IOException {

        var fileInfo = fileRepository.store(file);

        var fileMgmt = fileMgmtMapper.toFileMgmt(fileInfo);
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        fileMgmt.setOwnerId(userId);

        fileMgmtRepository.save(fileMgmt);

        return FileResponse.builder()
                .url(fileInfo.getUrl())
                .originalFilename(file.getOriginalFilename())
                .build();
    }

    @Override
    public FileData downloadMedia(String fileName) throws IOException {
        var fileMgmt = fileMgmtRepository.findById(fileName)
                .orElseThrow(() -> new AppException(ErrorCode.FILE_NOT_FOUND));

        var resource = fileRepository.read(fileMgmt);

        return new FileData(fileMgmt.getContentType(), resource);
    }

}
