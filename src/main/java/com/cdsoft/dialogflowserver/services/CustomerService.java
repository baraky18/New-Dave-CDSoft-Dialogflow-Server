package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.integrator.CustomerDetailsDto;
import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.mappers.CustomerDetailsDtoToCustomerMapper;
import com.cdsoft.dialogflowserver.mappers.CustomerToCustomerDetailsDtoMapper;
import com.cdsoft.dialogflowserver.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerToCustomerDetailsDtoMapper customerToCustomerDtoMapper;
    private final CustomerDetailsDtoToCustomerMapper customerDetailsDtoToCustomerMapper;
    private final RestTemplate integratorRestTemplate;

    public CustomerDetailsDto getCustomerDetails(String phoneNumber) {
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(phoneNumber);
        if(customerOptional.isEmpty()){
            CustomerDetailsDto customerDetailsDto = integratorRestTemplate.getForObject("/customer/phone-number/" + phoneNumber, CustomerDetailsDto.class);
            if(customerDetailsDto == null || customerDetailsDto.getFirstName() == null || customerDetailsDto.getFirstName().isEmpty()){
                return CustomerDetailsDto.builder().build();
            }
            customerRepository.save(customerDetailsDtoToCustomerMapper.map(customerDetailsDto));
            return customerDetailsDto;
        }
        return customerToCustomerDtoMapper.map(customerOptional.get());
    }
}
