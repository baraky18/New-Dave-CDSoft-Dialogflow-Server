package com.cdsoft.dialogflowserver.services.integrator;

import com.cdsoft.dialogflowserver.dtos.integrator.CustomerDetailsDto;
import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.mappers.CustomerDetailsDtoToCustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class IntegratorCustomerService {

    private final RestTemplate integratorRestTemplate;
    private final CustomerDetailsDtoToCustomerMapper customerDetailsDtoToCustomerMapper;

    public Customer getCustomerDetails(String phoneNumber) throws Exception {
        CustomerDetailsDto customerDetailsDto = integratorRestTemplate.getForObject("/customer/phone-number/" + phoneNumber, CustomerDetailsDto.class);
        if(isCustomerDetailsDtoEmpty(customerDetailsDto)){
            throw new Exception("customer is not found in integrator DB");
        }
        return customerDetailsDtoToCustomerMapper.map(customerDetailsDto);
    }

    private boolean isCustomerDetailsDtoEmpty(CustomerDetailsDto customerDetailsDto){
        return customerDetailsDto == null || customerDetailsDto.getFirstName() == null || customerDetailsDto.getFirstName().isEmpty();
    }
}
