package com.cdsoft.dialogflowserver.dtos.whatsapp;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WhatsappMessageDto {

    private String text;
    private LocalDateTime sentDateTime;
}
