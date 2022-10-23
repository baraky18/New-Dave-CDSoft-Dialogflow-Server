package com.cdsoft.dialogflowserver.mappers;

import com.cdsoft.dialogflowserver.dtos.customer.CustomerDetailsDto;
import com.cdsoft.dialogflowserver.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDetailsDtoMapper implements Mapper<CustomerDetailsDto, Customer>{

    @Override
    public CustomerDetailsDto map(Customer customer) {
        return CustomerDetailsDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }
}
