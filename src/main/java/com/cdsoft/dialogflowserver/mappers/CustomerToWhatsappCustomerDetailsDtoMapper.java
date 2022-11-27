package com.cdsoft.dialogflowserver.mappers;

import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappAddressDto;
import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappCustomerDetailsDto;
import com.cdsoft.dialogflowserver.entities.Address;
import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerToWhatsappCustomerDetailsDtoMapper implements Mapper<WhatsappCustomerDetailsDto, Customer>{

    private final SessionToWhatsappSessionDtoMapper sessionToWhatsappSessionDtoMapper;
    private final AddressToWhatsappAddressDtoMapper addressToWhatsappAddressDtoMapper;

    @Override
    public WhatsappCustomerDetailsDto map(Customer customer) {
        return WhatsappCustomerDetailsDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .gender(customer.getGender().name())
                .phoneNumber(customer.getPhoneNumber())
                .whatsappSessionDto(sessionToWhatsappSessionDtoMapper.map(customer.getSession()))
                .whatsappAddressDtoList(mapAddresses(customer.getAddresses()))
                .build();
    }

    private List<WhatsappAddressDto> mapAddresses(List<Address> addresses) {
        List<WhatsappAddressDto> whatsappAddresses = new ArrayList<>();
        addresses.stream().forEach(a -> whatsappAddresses.add(addressToWhatsappAddressDtoMapper.map(a)));
        return whatsappAddresses;
    }

    public Customer remap(WhatsappCustomerDetailsDto whatsappCustomerDetailsDto){
        return Customer.builder()
                .firstName(whatsappCustomerDetailsDto.getFirstName())
                .lastName(whatsappCustomerDetailsDto.getLastName())
                .gender(Gender.valueOf(whatsappCustomerDetailsDto.getGender()))
                .phoneNumber(whatsappCustomerDetailsDto.getPhoneNumber())
                .session(sessionToWhatsappSessionDtoMapper.remap(whatsappCustomerDetailsDto.getWhatsappSessionDto()))
                .addresses(remapAddresses(whatsappCustomerDetailsDto.getWhatsappAddressDtoList()))
                .build();
    }

    private List<Address> remapAddresses(List<WhatsappAddressDto> whatsappAddressDtoList) {
        List<Address> addresses = new ArrayList<>();
        whatsappAddressDtoList.stream().forEach(a -> addresses.add(addressToWhatsappAddressDtoMapper.remap(a)));
        return addresses;
    }
}
