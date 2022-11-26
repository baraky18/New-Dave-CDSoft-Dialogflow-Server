package com.cdsoft.dialogflowserver.mappers;

import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappCustomerDetailsDto;
import com.cdsoft.dialogflowserver.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerToWhatsappCustomerDetailsDtoMapper implements Mapper<WhatsappCustomerDetailsDto, Customer>{

    private final SessionToWhatsappSessionDtoMapper sessionToWhatsappSessionDtoMapper;

    @Override
    public WhatsappCustomerDetailsDto map(Customer customer) {
        return WhatsappCustomerDetailsDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .gender(customer.getGender().name())
                .phoneNumber(customer.getPhoneNumber())
                .whatsappSessionDto(sessionToWhatsappSessionDtoMapper.map(customer.getSession()))
                .build();
    }
}
