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
public class WhatsappCustomerDetailsDto {

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private WhatsappSessionDto whatsappSessionDto;
    private List<WhatsappAddressDto> whatsappAddressDtoList;
}
