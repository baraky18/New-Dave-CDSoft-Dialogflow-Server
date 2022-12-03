package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.entities.Session;
import com.cdsoft.dialogflowserver.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;
    private final CustomerService customerService;


    public void patchSessionUuid(String phoneNumber, String sessionUuid) throws Exception {
        Customer customer = customerService.getInternalCustomerDetails(phoneNumber);
        Session session = customer.getSession();
        session.setSessionUuid(UUID.fromString(sessionUuid));
        sessionRepository.save(session);
    }
}
