package com.minhdang.notification.service;

import com.minhdang.notification.dto.request.SendEmailRequest;
import com.minhdang.notification.dto.response.EmailResponse;

public interface EmailService {
    EmailResponse sendEmail(SendEmailRequest request);
}
