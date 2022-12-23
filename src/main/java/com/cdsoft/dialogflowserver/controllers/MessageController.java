package com.cdsoft.dialogflowserver.controllers;

import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappHandleMessageResponseDto;
import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappMessageDto;
import com.cdsoft.dialogflowserver.services.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/message")
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService messageService;

    @PostMapping(path = "/phone-number/{phoneNumber}")
    @ResponseBody
    public WhatsappHandleMessageResponseDto createMessage(@PathVariable String phoneNumber, @RequestBody WhatsappMessageDto whatsappMessageDto) throws Exception {
        log.info("MessageController.createMessage\n phoneNumber is: "+ phoneNumber + " message is: " + whatsappMessageDto);
        return messageService.createMessage(phoneNumber, whatsappMessageDto);
    }
}
