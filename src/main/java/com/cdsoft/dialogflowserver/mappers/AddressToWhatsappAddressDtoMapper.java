package com.cdsoft.dialogflowserver.mappers;

import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappAddressDto;
import com.cdsoft.dialogflowserver.entities.Address;
import com.cdsoft.dialogflowserver.entities.City;
import com.cdsoft.dialogflowserver.entities.Country;
import org.springframework.stereotype.Component;

@Component
public class AddressToWhatsappAddressDtoMapper implements Mapper<WhatsappAddressDto, Address>{

    @Override
    public WhatsappAddressDto map(Address address) {
        return WhatsappAddressDto.builder()
                .country(address.getCountry().getCountryName())
                .city(address.getCity().getCityName())
                .streetName(address.getStreetName())
                .streetNumber(address.getStreetNumber())
                .houseNumber(address.getHouseNumber())
                .build();
    }

    public Address remap(WhatsappAddressDto whatsappAddressDto) {
        return Address.builder()
                .country(Country.builder().countryName(whatsappAddressDto.getCountry()).build())
                .city(City.builder().cityName(whatsappAddressDto.getCity()).build())
                .streetName(whatsappAddressDto.getStreetName())
                .streetNumber(whatsappAddressDto.getStreetNumber())
                .houseNumber(whatsappAddressDto.getHouseNumber())
                .build();
    }
}
