package com.cdsoft.dialogflowserver.dtos.whatsapp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhatsappSessionDto {

    private String sessionName;
    private List<WhatsappMessageDto> whatsappMessageDtoList;
}
