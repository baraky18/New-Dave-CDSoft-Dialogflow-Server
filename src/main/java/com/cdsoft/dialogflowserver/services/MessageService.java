package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappMessageDto;
import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.entities.Message;
import com.cdsoft.dialogflowserver.mappers.MessageToWhatsappMessageDtoMapper;
import com.cdsoft.dialogflowserver.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final CustomerService customerService;
    private final MessageToWhatsappMessageDtoMapper messageToWhatsappMessageDtoMapper;
    private final MessageRepository messageRepository;

    public void createMessage(String phoneNumber, WhatsappMessageDto whatsappMessageDto) throws Exception {
        log.info("MessageService.createMessage");
        Customer customer = customerService.getInternalCustomerDetails(phoneNumber);
        Message message = messageToWhatsappMessageDtoMapper.remap(whatsappMessageDto);
        message.setSession(customer.getSession());
        log.info("message is: "+ message);
        messageRepository.save(message);
    }
}
