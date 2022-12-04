package com.cdsoft.dialogflowserver.controllers;

import com.cdsoft.dialogflowserver.dtos.whatsapp.WhatsappCustomerDetailsDto;
import com.cdsoft.dialogflowserver.services.CustomerService;
import com.cdsoft.dialogflowserver.services.SessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final SessionService sessionService;

    @GetMapping(path = "/phone-number/{phoneNumber}")
    @ResponseBody
    public WhatsappCustomerDetailsDto getCustomerDetails(@PathVariable String phoneNumber) throws Exception {
        log.info("CustomerController.getCustomerDetails\n phoneNumber is: "+ phoneNumber);
        return customerService.getCustomerDetails(phoneNumber);
    }

    @PostMapping
    public void createCustomerDetails(@RequestBody WhatsappCustomerDetailsDto whatsappCustomerDetailsDto) {
        log.info("CustomerController.createCustomerDetails\n phoneNumber is: "+ whatsappCustomerDetailsDto.getPhoneNumber());
        customerService.createCustomerDetails(whatsappCustomerDetailsDto);
    }

    @PatchMapping(path = "/session-uuid/{phoneNumber}")
    public String patchNewCustomerSessionUuid(@PathVariable String phoneNumber, @RequestBody String sessionUuid) throws Exception {
        log.info("CustomerController.patchNewCustomerSessionUuid\n phoneNumber is: "+ phoneNumber);
        return sessionService.patchSessionUuid(phoneNumber);
    }
}
