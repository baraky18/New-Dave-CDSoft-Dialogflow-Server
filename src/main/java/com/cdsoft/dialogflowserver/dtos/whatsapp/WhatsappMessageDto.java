package com.cdsoft.dialogflowserver.dtos.whatsapp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class WhatsappMessageDto {

    private String text;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date sentDate;
}
