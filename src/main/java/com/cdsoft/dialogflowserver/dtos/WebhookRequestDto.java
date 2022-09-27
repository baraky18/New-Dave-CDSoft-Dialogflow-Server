package com.cdsoft.dialogflowserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebhookRequestDto {

    private String detectIntentResponseId;
    private IntentInfoDto intentInfo;
    private PageInfoDto pageInfo;
    private SessionInfoDto sessionInfo;
    private FulfillmentInfoDto fulfillmentInfo;
    private List<MessageDto> messages;
    private String text;
    private String languageCode;
}
