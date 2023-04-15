package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.google.WebhookRequestDto;
import com.cdsoft.dialogflowserver.dtos.google.WebhookResponseDto;
import com.cdsoft.dialogflowserver.dtos.integrator.FeatureValueDto;
import com.cdsoft.dialogflowserver.dtos.integrator.ProductDetailsDto;
import com.cdsoft.dialogflowserver.dtos.integrator.ProductsDetailsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimilarProductsService {

    private final ProductService productService;
    private final RestTemplate integratorRestTemplate;

    public WebhookResponseDto getSimilarProductsDetails(WebhookRequestDto webhookRequestDto) {
        ProductDetailsDto productDetailsDto = productService.getProductDetails(webhookRequestDto);
        List<FeatureValueDto> featureValueDtoList = getPreferredFeaturesAndValues(productDetailsDto);
        ProductsDetailsDto productsDetailsDto = integratorRestTemplate.postForObject("/similar-products", featureValueDtoList, ProductsDetailsDto.class);
        return prepareWebhookResponse(productsDetailsDto);
    }

    private WebhookResponseDto prepareWebhookResponse(ProductsDetailsDto productsDetailsDto) {
        return null;
    }

    private List<FeatureValueDto> getPreferredFeaturesAndValues(ProductDetailsDto productDetailsDto) {
        return null;
    }
}
