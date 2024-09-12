package com.learning.notification_service.controller;

import com.learning.event.dto.NotificationEvent;
import com.learning.notification_service.dto.request.Recipient;
import com.learning.notification_service.dto.request.SendEmailRequest;
import com.learning.notification_service.repository.httpclient.EmailClient;
import com.learning.notification_service.service.EmailService;
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
    public void listenNotificationDelivery(NotificationEvent notificationEvent) {
        log.info("Email Response: " + notificationEvent);
        emailService.sendEmail(SendEmailRequest.builder()
                        .to(Recipient.builder()
                                .email(notificationEvent.getRecipient())
                                .build())
                        .subject(notificationEvent.getSubject())
                        .htmlContent(notificationEvent.getBody())
                .build());
    }
}
