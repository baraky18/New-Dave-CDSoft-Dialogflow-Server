package com.cdsoft.dialogflowserver.repositories;

import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.entities.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    Optional<Customer> findBySession(Session session);
}
