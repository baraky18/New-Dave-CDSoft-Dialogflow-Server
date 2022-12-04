package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.entities.Session;
import com.cdsoft.dialogflowserver.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;
    private final CustomerService customerService;


    public String patchSessionUuid(String phoneNumber) throws Exception {
        Customer customer = customerService.getInternalCustomerDetails(phoneNumber);
        byte[] uuidByteArray = UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8);
        String utf8EncodedSessionUuid = new String(uuidByteArray, StandardCharsets.UTF_8);
        Session session = customer.getSession();
        session.setSessionUuid(utf8EncodedSessionUuid);
        sessionRepository.save(session);
        return utf8EncodedSessionUuid;
    }
}
