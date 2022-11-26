package com.cdsoft.dialogflowserver.mappers;

import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappMessageDto;
import com.cdsoft.dialogflowserver.entities.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageToWhatsappMessageDtoMapper implements Mapper<WhatsappMessageDto, Message>{

    @Override
    public WhatsappMessageDto map(Message message) {
        return null;
    }
}
