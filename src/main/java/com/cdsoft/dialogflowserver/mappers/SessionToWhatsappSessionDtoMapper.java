package com.cdsoft.dialogflowserver.mappers;

import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappMessageDto;
import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappSessionDto;
import com.cdsoft.dialogflowserver.entities.Message;
import com.cdsoft.dialogflowserver.entities.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SessionToWhatsappSessionDtoMapper implements Mapper<WhatsappSessionDto, Session>{

    private final MessageToWhatsappMessageDtoMapper messageToWhatsappMessageDtoMapper;

    @Override
    public WhatsappSessionDto map(Session session) {
        return WhatsappSessionDto.builder()
                .sessionName(session.getSessionName())
                .whatsappMessageDtoList(mapMessages(session.getMessages()))
                .build();
    }

    private List<WhatsappMessageDto> mapMessages(List<Message> messages){
        List<WhatsappMessageDto> WhatsappMessages = new ArrayList<>();
        messages.stream().forEach(m -> WhatsappMessages.add(messageToWhatsappMessageDtoMapper.map(m)));
        return WhatsappMessages;
    }

    public Session remap(WhatsappSessionDto whatsappSessionDto) {
        return Session.builder()
                .sessionName(whatsappSessionDto.getSessionName())
                .messages(remapMessages(whatsappSessionDto.getWhatsappMessageDtoList()))
                .build();
    }

    private List<Message> remapMessages(List<WhatsappMessageDto> whatsappMessageDtoList) {
        List<Message> messages = new ArrayList<>();
        whatsappMessageDtoList.stream().forEach(m -> messages.add(messageToWhatsappMessageDtoMapper.remap(m)));
        return messages;
    }
}
