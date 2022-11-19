package com.cdsoft.dialogflowserver.mappers;

import com.cdsoft.dialogflowserver.dtos.integrator.CustomerDetailsDto;
import com.cdsoft.dialogflowserver.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailsDtoToCustomerMapper implements Mapper<Customer, CustomerDetailsDto>{

    @Override
    public Customer map(CustomerDetailsDto customerDetailsDto) {
        return Customer.builder()
                .firstName(customerDetailsDto.getFirstName())
                .lastName(customerDetailsDto.getLastName())
                .phoneNumber(customerDetailsDto.getPhoneNumber())
                .build();
    }
}
