package com.cdsoft.dialogflowserver.dtos.whatsapp;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WhatsappMessageMetadataDto {

    private boolean isNewMessage;
    private boolean isOpeningSessionMessage;
}
