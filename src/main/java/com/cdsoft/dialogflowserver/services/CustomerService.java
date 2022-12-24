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

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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
            return getCustomerFromIntegrator(phoneNumber);
        }
        Customer customer = customerOptional.get();
        log.info("customer is: "+ customer);
        return customerToWhatsappCustomerDetailsDtoMapper.map(customer);
    }

    private WhatsappCustomerDetailsDto getCustomerFromIntegrator(String phoneNumber) throws Exception {
        Customer customer = integratorCustomerService.getCustomerDetails(phoneNumber);
        customerRepository.save(customer);
        return customerToWhatsappCustomerDetailsDtoMapper.map(customer);
    }

    public WhatsappCustomerDetailsDto createCustomerDetails(String phoneNumber) {
        Customer customer = Customer.builder()
                .phoneNumber(phoneNumber)
                .session(Session.builder()
                        .sessionName(phoneNumber)
                        .lastUpdated(LocalDateTime.now())
                        .sessionUuid(UUID.randomUUID().toString())
                        .build())
                .build();
        customerRepository.save(customer);
        return customerToWhatsappCustomerDetailsDtoMapper.map(customer);
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
        return customerRepository.getCustomerBySession(session.getSessionUuid());
    }
}
