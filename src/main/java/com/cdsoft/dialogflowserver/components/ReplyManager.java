package com.cdsoft.dialogflowserver.components;

import com.cdsoft.dialogflowserver.dtos.WebhookRequestDto;
import com.cdsoft.dialogflowserver.dtos.WebhookResponseDto;
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

    public WebhookResponseDto handleProductDetailsRequest(WebhookRequestDto webhookRequestDto) {
        return productService.getProductDetails(webhookRequestDto);
    }

    public WebhookResponseDto handleDayToDateRequest(WebhookRequestDto webhookRequestDto) {
        return dayAndTimeService.getDateFromDay(webhookRequestDto);
    }
}
