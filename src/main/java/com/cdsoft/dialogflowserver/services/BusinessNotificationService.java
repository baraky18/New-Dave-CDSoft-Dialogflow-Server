package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.google.WebhookRequestDto;
import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.entities.Session;
import com.cdsoft.dialogflowserver.enums.BusinessNotificationType;
import com.cdsoft.dialogflowserver.model.BusinessNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.cdsoft.dialogflowserver.util.DialogflowEntities.PRODUCT_DETAILS_ENTITY;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessNotificationService {

    private final DayAndTimeService dayAndTimeService;
    private final JavaMailSender emailSender;
    private final SessionService sessionService;
    private final CustomerService customerService;

    @Value("${email.from}")
    private String fromEmail;
    @Value("${email.to}")
    private String toEmail;

    public void notifyBusiness(BusinessNotificationType businessNotificationType, WebhookRequestDto webhookRequestDto) {
        BusinessNotification businessNotification = createBusinessNotification(businessNotificationType, webhookRequestDto);
        sendNotification(businessNotification);
    }

    private void sendNotification(BusinessNotification businessNotification) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject(businessNotification.getBusinessNotificationType().getBusinessNotificationTypeValue());
            message.setText(businessNotification.toString());
            emailSender.send(message);
    }

    private BusinessNotification createBusinessNotification(BusinessNotificationType businessNotificationType, WebhookRequestDto webhookRequestDto) {
        LocalDateTime requestedDateAndTime = dayAndTimeService.getRequestedDateAndTime(webhookRequestDto);
        String productDetails = webhookRequestDto.getSessionInfo().getParameters().get(PRODUCT_DETAILS_ENTITY);
        String customerDetails = getCustomerDetailsFromSession(webhookRequestDto);
        return BusinessNotification.builder()
                .productDetails(productDetails)
                .customerDetails(customerDetails)
                .requestedDateTime(requestedDateAndTime)
                .businessNotificationType(businessNotificationType)
                .build();
    }

    private String getCustomerDetailsFromSession(WebhookRequestDto webhookRequestDto) {
        String requestSession = webhookRequestDto.getSessionInfo().getSession();
        String sessionUuid = getSessionUuidFromSession(requestSession);
        log.info("sessionUuid is: " + sessionUuid);
        Session session = sessionService.getSessionByUuid(sessionUuid);
        log.info("session.getLastUpdated is: " + session.getLastUpdated());
        log.info("session.getSessionUuid is: " + session.getSessionUuid());
        log.info("session.getSessionName is: " + session.getSessionName());
        Customer customer = customerService.getCustomerBySession(session);
        return "First name: " + customer.getFirstName() + " Last name: " + customer.getLastName() + " Phone number: " + customer.getPhoneNumber();
    }

    private String getSessionUuidFromSession(String session) {
        int sessionUuidBeginIndex = session.lastIndexOf("/sessions/") + 10;
        return session.substring(sessionUuidBeginIndex);
    }
}
