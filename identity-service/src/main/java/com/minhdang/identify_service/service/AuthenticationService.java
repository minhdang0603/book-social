package com.minhdang.identify_service.service;

import java.text.ParseException;

import com.minhdang.identify_service.dto.request.AuthenticationRequest;
import com.minhdang.identify_service.dto.request.IntrospectRequest;
import com.minhdang.identify_service.dto.request.LogoutRequest;
import com.minhdang.identify_service.dto.request.RefreshRequest;
import com.minhdang.identify_service.dto.response.AuthenticationResponse;
import com.minhdang.identify_service.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void logout(LogoutRequest request) throws JOSEException, ParseException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
