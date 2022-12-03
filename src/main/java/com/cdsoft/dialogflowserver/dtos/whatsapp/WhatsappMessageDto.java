package com.cdsoft.dialogflowserver.dtos.whatsapp;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WhatsappMessageDto {

    private String text;
    private Date sentDate;
}
