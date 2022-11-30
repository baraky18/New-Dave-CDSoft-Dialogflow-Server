package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappCustomerDetailsDto;
import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.mappers.CustomerToWhatsappCustomerDetailsDtoMapper;
import com.cdsoft.dialogflowserver.repositories.CustomerRepository;
import com.cdsoft.dialogflowserver.services.integrator.IntegratorCustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerToWhatsappCustomerDetailsDtoMapper customerToWhatsappCustomerDetailsDtoMapper;
    private final IntegratorCustomerService integratorCustomerService;

    public WhatsappCustomerDetailsDto getCustomerDetails(String phoneNumber) throws Exception {
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(phoneNumber);
        if(customerOptional.isEmpty()){
            Customer customer = integratorCustomerService.getCustomerDetails(phoneNumber);
            customerRepository.save(customer);
            return customerToWhatsappCustomerDetailsDtoMapper.map(customer);
        }
        log.info("customer is: "+ customerOptional.get().getCustomerId() + customerOptional.get().getFirstName() + customerOptional.get().getLastName() + customerOptional.get().getGender() + customerOptional.get().getAddresses() + customerOptional.get().getSession());
        return customerToWhatsappCustomerDetailsDtoMapper.map(customerOptional.get());
    }

    public void createCustomerDetails(WhatsappCustomerDetailsDto whatsappCustomerDetailsDto) {
        customerRepository.save(customerToWhatsappCustomerDetailsDtoMapper.remap(whatsappCustomerDetailsDto));
    }
}
