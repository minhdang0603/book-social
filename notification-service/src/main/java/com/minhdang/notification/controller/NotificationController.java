package com.minhdang.notification.controller;

import com.minhdang.event.dto.NotificationEvent;
import com.minhdang.notification.dto.request.Recipient;
import com.minhdang.notification.dto.request.SendEmailRequest;
import com.minhdang.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {

    EmailService emailService;

    @KafkaListener(topics = "notification-delivery")
    public void listenNotificationDelivery(NotificationEvent message) {
        log.info("Message received: {}", message);
        emailService.sendEmail(SendEmailRequest.builder()
                .to(Recipient.builder()
                        .email(message.getRecipient())
                        .build())
                .htmlContent(message.getBody())
                .subject(message.getSubject())
                .build());
    }

}
