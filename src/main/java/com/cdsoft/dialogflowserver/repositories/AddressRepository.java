package com.cdsoft.dialogflowserver.repositories;

import com.cdsoft.dialogflowserver.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

    Optional<Address> findByPhone(String phoneNumber);
}
