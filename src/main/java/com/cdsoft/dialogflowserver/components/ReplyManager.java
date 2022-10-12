package com.cdsoft.dialogflowserver.components;

import com.cdsoft.dialogflowserver.dtos.WebhookRequestDto;
import com.cdsoft.dialogflowserver.dtos.WebhookResponseDto;
import com.cdsoft.dialogflowserver.enums.BusinessNotificationType;
import com.cdsoft.dialogflowserver.services.BusinessNotificationService;
import com.cdsoft.dialogflowserver.services.DayAndTimeService;
import com.cdsoft.dialogflowserver.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReplyManager {

    private final ProductService productService;
    private final DayAndTimeService dayAndTimeService;
    private final BusinessNotificationService businessNotificationService;

    public WebhookResponseDto handleProductDetailsRequest(WebhookRequestDto webhookRequestDto) {
        log.info("ReplyManager.handleProductDetailsRequest");
        return productService.getProductDetails(webhookRequestDto);
    }

    public WebhookResponseDto handleBusinessCallNotificationRequest(BusinessNotificationType businessNotificationType, WebhookRequestDto webhookRequestDto) {
        log.info("ReplyManager.handleBusinessCallNotificationRequest");
        businessNotificationService.notifyBusiness(businessNotificationType, webhookRequestDto);
        return dayAndTimeService.prepareWebhookResponse(webhookRequestDto);
    }
}
