package com.minhdang.notification.service.impl;

import com.minhdang.notification.dto.request.EmailRequest;
import com.minhdang.notification.dto.request.SendEmailRequest;
import com.minhdang.notification.dto.request.Sender;
import com.minhdang.notification.dto.response.EmailResponse;
import com.minhdang.notification.exception.AppException;
import com.minhdang.notification.exception.ErrorCode;
import com.minhdang.notification.repository.httpclient.EmailClient;
import com.minhdang.notification.service.EmailService;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailServiceImpl implements EmailService {

    EmailClient emailClient;

    @Value("${EMAIL_API_KEY}")
    @NonFinal
    private String brevoApiKey;

    @Override
    public EmailResponse sendEmail(SendEmailRequest request) {

        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("soleilnoir.com")
                        .email("ruabin1163@gmail.com")
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();

        try {
            log.info("Api key {}", brevoApiKey);
            return emailClient.sendEmail(brevoApiKey, emailRequest);
        } catch (FeignException e) {
            log.error("Error sending email", e);
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }

}
