package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.google.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DialogflowWebhookResponseService {

    public WebhookResponseDto populateWebhookResponseDto(List<String> reply, Map<String, String> params) {
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
