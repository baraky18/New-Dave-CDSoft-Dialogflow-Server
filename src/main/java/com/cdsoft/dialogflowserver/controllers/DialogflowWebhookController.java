package com.cdsoft.dialogflowserver.controllers;

import com.cdsoft.dialogflowserver.components.ReplyManager;
import com.cdsoft.dialogflowserver.dtos.*;
import com.cdsoft.dialogflowserver.entities.ProductDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.cdsoft.dialogflowserver.enums.BusinessNotificationType.PERFORM_BUSINESS_CALL;
import static com.cdsoft.dialogflowserver.util.Constants.UNWANTED_TEXT_FOR_PRODUCT_DETAILS_MSG;
import static com.cdsoft.dialogflowserver.util.DialogflowEntities.*;
import static com.cdsoft.dialogflowserver.util.LogUtil.formatJsonString;

@RestController
@RequestMapping(path = "/dialogflow-webhook")
@RequiredArgsConstructor
@Slf4j
public class DialogflowWebhookController {

    private final ReplyManager replyManager;
    private final ObjectMapper objectMapper;

    @PostMapping(path = "/product-details")
    @ResponseBody
    public WebhookResponseDto productDetails(@RequestBody String webhookRequestDtoAsString) throws JsonProcessingException {
        log.info("DialogflowWebhookController.productDetails\n webhookRequest is: \n"+ formatJsonString(webhookRequestDtoAsString));
        WebhookRequestDto webhookRequestDto = objectMapper.readValue(webhookRequestDtoAsString, WebhookRequestDto.class);
        WebhookResponseDto webhookResponseDto = replyManager.handleProductDetailsRequest(webhookRequestDto);
        log.info("webhookResponse is: \n" + objectMapper.writeValueAsString(webhookResponseDto));
        return webhookResponseDto;
    }

    @PostMapping(path = "/business-call-notification")
    @ResponseBody
    public WebhookResponseDto businessCallNotification(@RequestBody String webhookRequestDtoAsString) throws JsonProcessingException {
        log.info("DialogflowWebhookController.businessCallNotification\n webhookRequest is: \n" + formatJsonString(webhookRequestDtoAsString));
        WebhookRequestDto webhookRequestDto = objectMapper.readValue(webhookRequestDtoAsString, WebhookRequestDto.class);
        WebhookResponseDto webhookResponseDto = replyManager.handleBusinessCallNotificationRequest(PERFORM_BUSINESS_CALL, webhookRequestDto);
        log.info("webhookResponse is: \n" + objectMapper.writeValueAsString(webhookResponseDto));
        return webhookResponseDto;
    }

    @PostMapping(path = "/product-details-mock")
    @ResponseBody
    public WebhookResponseDto productDetailsMock(@RequestBody String webhookRequestDtoAsString) throws JsonProcessingException {
        log.info("DialogflowWebhookController.productDetailsMock");
        log.info("webhookRequest is: \n" + formatJsonString(webhookRequestDtoAsString));

        ArrayList<String> reply = new ArrayList<>();
        reply.add("זה מדפסת/ סורק מעולה של Ultimaker. מה תרצה לדעת עליו?");

        WebhookRequestDto webhookRequestDto = objectMapper.readValue(webhookRequestDtoAsString, WebhookRequestDto.class);
        String productNameWithStars = webhookRequestDto.getText().replace(UNWANTED_TEXT_FOR_PRODUCT_DETAILS_MSG, "");
        String productName = productNameWithStars.replace("*", "");

        Map<String, String> params = new HashMap<>();
        params.put(STOCK_ENTITY, "true");
        params.put(PRICE_ENTITY, "52.3$");
        params.put(SUPPLY_TIME_ENTITY, "2021-11-16");
        params.put(PRODUCT_DETAILS_ENTITY, productName);
        MessageDto message = MessageDto.builder()
                .text(TextDto.builder()
                        .text(reply).build()).build();
        ArrayList<MessageDto> messages = new ArrayList<>();
        messages.add(message);
        return WebhookResponseDto.builder()
                .sessionInfoDto(SessionInfoDto.builder()
                        .parameters(params).build())
                .fulfillmentResponseDto(FulfillmentResponseDto.builder()
                        .messages(messages).build()).build();
    }
}
