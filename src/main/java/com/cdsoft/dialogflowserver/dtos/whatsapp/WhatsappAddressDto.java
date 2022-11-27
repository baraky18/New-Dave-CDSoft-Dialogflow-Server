package com.cdsoft.dialogflowserver.dtos.whatsapp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WhatsappAddressDto {

    private String country;
    private String city;
    private String streetName;
    private int streetNumber;
    private int houseNumber;
}
