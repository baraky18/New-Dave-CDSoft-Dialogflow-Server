package com.cdsoft.dialogflowserver.dtos.whatsapp;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WhatsappHandleMessageResponseDto {

    private boolean messageAlreadyExist;
}
