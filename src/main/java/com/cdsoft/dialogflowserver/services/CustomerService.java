package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappCustomerDetailsDto;
import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.entities.Session;
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
        log.info("CustomerService.getCustomerDetails");
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(phoneNumber);
        if(customerOptional.isEmpty()){
            Customer customer = integratorCustomerService.getCustomerDetails(phoneNumber);
            customerRepository.save(customer);
            return customerToWhatsappCustomerDetailsDtoMapper.map(customer);
        }
        log.info("customer is: "+ customerOptional.get());
        return customerToWhatsappCustomerDetailsDtoMapper.map(customerOptional.get());
    }

    public void createCustomerDetails(WhatsappCustomerDetailsDto whatsappCustomerDetailsDto) {
        customerRepository.save(customerToWhatsappCustomerDetailsDtoMapper.remap(whatsappCustomerDetailsDto));
    }

    public Customer getInternalCustomerDetails(String phoneNumber) throws Exception {
        log.info("CustomerService.getInternalCustomerDetails");
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(phoneNumber);
        if(customerOptional.isEmpty()){
            throw new Exception("customer is not found in New Dave's DB");
        }
        log.info("customer is: "+ customerOptional.get());
        return customerOptional.get();
    }

    public Customer getCustomerBySession(Session session){
//        return customerRepository.findBySession(session).orElseThrow();
        return customerRepository.getCustomerBySession(session);
    }
}
