package com.minhdang.identity.repository.httpclient;

import com.minhdang.identity.configuration.AuthenticationRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.minhdang.identity.dto.request.ProfileCreationRequest;
import com.minhdang.identity.dto.response.UserProfileResponse;

@FeignClient(
        name = "profile-service",
        url = "${app.services.profile}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface ProfileClient {
    @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse createProfile(@RequestBody ProfileCreationRequest request);
}
