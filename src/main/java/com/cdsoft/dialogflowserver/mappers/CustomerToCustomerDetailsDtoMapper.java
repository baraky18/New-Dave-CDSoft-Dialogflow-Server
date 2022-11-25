package com.cdsoft.dialogflowserver.mappers;

import com.cdsoft.dialogflowserver.dtos.integrator.CustomerDetailsDto;
import com.cdsoft.dialogflowserver.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDetailsDtoMapper implements Mapper<CustomerDetailsDto, Customer>{

    @Override
    public CustomerDetailsDto map(Customer customer) {
        return CustomerDetailsDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .gender(customer.getGender().name())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
