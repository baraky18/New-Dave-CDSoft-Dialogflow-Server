package com.cdsoft.dialogflowserver.components;

import com.cdsoft.dialogflowserver.dtos.google.WebhookRequestDto;
import com.cdsoft.dialogflowserver.dtos.google.WebhookResponseDto;
import com.cdsoft.dialogflowserver.enums.BusinessNotificationType;
import com.cdsoft.dialogflowserver.services.BusinessNotificationService;
import com.cdsoft.dialogflowserver.services.DayAndTimeService;
import com.cdsoft.dialogflowserver.services.ProductService;
import com.cdsoft.dialogflowserver.services.SimilarProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.cdsoft.dialogflowserver.util.LogUtil.formatJsonString;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReplyManager {

    private final ProductService productService;
    private final SimilarProductsService similarProductsService;
    private final DayAndTimeService dayAndTimeService;
    private final BusinessNotificationService businessNotificationService;

    public WebhookResponseDto handleProductDetailsRequest(WebhookRequestDto webhookRequestDto) {
        log.info("ReplyManager.handleProductDetailsRequest");
        return productService.getProductDetailsAsWebhookResponse(webhookRequestDto);
    }

    public WebhookResponseDto handleSimilarProductsDetailsRequest(WebhookRequestDto webhookRequestDto) {
        log.info("ReplyManager.handleSimilarProductsDetailsRequest");
        return similarProductsService.getSimilarProductsDetails(webhookRequestDto);
    }

    public WebhookResponseDto handleBusinessCallNotificationRequest(BusinessNotificationType businessNotificationType, WebhookRequestDto webhookRequestDto) {
        log.info("ReplyManager.handleBusinessCallNotificationRequest");
        businessNotificationService.notifyBusiness(businessNotificationType, webhookRequestDto);
        WebhookResponseDto webhookResponseDto = dayAndTimeService.prepareWebhookResponse(webhookRequestDto);
        log.info("webhookResponseDto is:\n" + webhookResponseDto.toString());
        return webhookResponseDto;
    }
}
