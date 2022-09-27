package com.cdsoft.dialogflowserver.controllers;

import com.cdsoft.dialogflowserver.components.ReplyManager;
import com.cdsoft.dialogflowserver.dtos.WebhookRequestDto;
import com.cdsoft.dialogflowserver.dtos.WebhookResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
        log.info("DialogflowWebhookController.productDetails");
        log.info("webhookRequest is: \n" + formatJsonString(webhookRequestDtoAsString));
        WebhookRequestDto webhookRequestDto = objectMapper.readValue(webhookRequestDtoAsString, WebhookRequestDto.class);
        WebhookResponseDto webhookResponseDto = replyManager.handleProductDetailsRequest(webhookRequestDto);
        log.info("webhookResponse is: \n" + objectMapper.writeValueAsString(webhookResponseDto));
        return webhookResponseDto;
    }
}
