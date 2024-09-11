package com.learning.notification_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.notification_service.dto.request.EmailRequest;
import com.learning.notification_service.dto.request.SendEmailRequest;
import com.learning.notification_service.dto.request.Sender;
import com.learning.notification_service.dto.response.EmailResponse;
import com.learning.notification_service.exception.AppException;
import com.learning.notification_service.exception.ErrorCode;
import com.learning.notification_service.repository.httpclient.EmailClient;

import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;
    String apiKey = "";

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("HuyTQ")
                        .email("huytq3103@gmail.com")
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();
        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException e) {
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
