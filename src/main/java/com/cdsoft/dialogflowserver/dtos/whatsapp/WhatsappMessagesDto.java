package com.cdsoft.dialogflowserver.dtos.whatsapp;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WhatsappMessagesDto {

    private List<WhatsappMessageDto> messages;
}
