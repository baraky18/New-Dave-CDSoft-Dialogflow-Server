package com.cdsoft.dialogflowserver.dtos.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WebhookResponseDto {

    @JsonProperty(value = "fulfillment_response")
    private FulfillmentResponseDto fulfillmentResponseDto;

    @JsonProperty(value = "session_info")
    private SessionInfoDto sessionInfoDto;
}
