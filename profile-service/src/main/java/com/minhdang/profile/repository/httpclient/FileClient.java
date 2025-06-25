package com.minhdang.profile.repository.httpclient;

import com.minhdang.profile.configuration.AuthenticationRequestInterceptor;
import com.minhdang.profile.dto.response.ApiResponse;
import com.minhdang.profile.dto.response.FileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(
        name = "file-service",
        url = "${app.services.file}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface FileClient {
    // Endpoint to upload media files
    @PostMapping(value = "/media/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ApiResponse<FileResponse> uploadMedia(@RequestPart("file") MultipartFile file);
}
