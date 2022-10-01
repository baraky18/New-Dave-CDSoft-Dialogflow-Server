package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.entities.ProductDetails;
import com.cdsoft.dialogflowserver.repositories.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

//    private final ProductDetailsRepository productDetailsRepository;

    public ProductDetails getProductDetails(String productName) {
        log.info("ProductDetailsService.get");
//        Optional<ProductDetails> productDetailsOptional = productDetailsRepository.findByProductName(productName);
//        return productDetailsOptional.orElseGet(ProductDetails::new);
        return new ProductDetails();
    }
}
