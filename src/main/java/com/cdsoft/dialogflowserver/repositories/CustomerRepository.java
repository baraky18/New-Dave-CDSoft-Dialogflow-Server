package com.cdsoft.dialogflowserver.repositories;

import com.cdsoft.dialogflowserver.entities.Customer;
import com.cdsoft.dialogflowserver.entities.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    @Query("select c from Customer c where c.customerId in (select s.customer from Session s where s.sessionUuid = (:sessionUuid))")
    Customer getCustomerBySession(@Param("sessionUuid") String sessionUuid);
}
