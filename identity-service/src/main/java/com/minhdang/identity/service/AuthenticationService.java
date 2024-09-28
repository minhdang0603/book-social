package com.minhdang.identity.service;

import java.text.ParseException;

import com.minhdang.identity.dto.request.AuthenticationRequest;
import com.minhdang.identity.dto.request.IntrospectRequest;
import com.minhdang.identity.dto.request.LogoutRequest;
import com.minhdang.identity.dto.request.RefreshRequest;
import com.minhdang.identity.dto.response.AuthenticationResponse;
import com.minhdang.identity.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

public interface AuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void logout(LogoutRequest request) throws JOSEException, ParseException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
