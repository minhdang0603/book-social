package com.minhdang.notification.controller;

import com.minhdang.notification.dto.request.SendEmailRequest;
import com.minhdang.notification.dto.response.ApiResponse;
import com.minhdang.notification.dto.response.EmailResponse;
import com.minhdang.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/email")
public class EmailController {

    EmailService emailService;

    @PostMapping("/send")
    ApiResponse<EmailResponse> sendMail(@RequestBody SendEmailRequest request) {
        return ApiResponse.<EmailResponse>builder()
                .result(emailService.sendEmail(request))
                .build();
    }

}
