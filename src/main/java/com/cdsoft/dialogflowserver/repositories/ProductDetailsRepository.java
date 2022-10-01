package com.cdsoft.dialogflowserver.repositories;

import com.cdsoft.dialogflowserver.entities.ProductDetails;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface ProductDetailsRepository {//extends CrudRepository<ProductDetails, String> {

    Optional<ProductDetails> findByProductName(String productName);
}
