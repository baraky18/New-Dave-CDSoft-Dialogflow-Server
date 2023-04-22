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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cdsoft.dialogflowserver.util.Constants.*;
import static com.cdsoft.dialogflowserver.util.DialogflowEntities.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimilarProductsService {

    private final ProductService productService;
    private final RestTemplate integratorRestTemplate;
    private final DialogflowWebhookResponseService dialogflowWebhookResponseService;

    public WebhookResponseDto getSimilarProductsDetails(WebhookRequestDto webhookRequestDto) {
        ProductDetailsDto productDetailsDto = productService.getProductDetails(webhookRequestDto);
        List<FeatureValueDto> featureValueDtoList = getPreferredFeaturesAndValues(productDetailsDto);
        ProductsDetailsDto productsDetailsDto = integratorRestTemplate.getForObject("/similar-products", ProductsDetailsDto.class, featureValueDtoList);
        return prepareWebhookResponse(productsDetailsDto);
    }

    private List<FeatureValueDto> getPreferredFeaturesAndValues(ProductDetailsDto productDetailsDto) {
        //TODO need to get the logic from Dvir
        List<FeatureValueDto> featureValueDtoList = new ArrayList<>();
        featureValueDtoList.add(productDetailsDto.getFeaturesValues().get(0));
        featureValueDtoList.add(productDetailsDto.getFeaturesValues().get(1));
        return featureValueDtoList;
    }

    private WebhookResponseDto prepareWebhookResponse(ProductsDetailsDto productsDetailsDto) {
        log.info("ProductService.prepareWebhookResponse");
        List<String> reply = populateReply(productsDetailsDto);
        Map<String, String> params = populateParams(productsDetailsDto);
        return dialogflowWebhookResponseService.populateWebhookResponseDto(reply, params);
    }

    private List<String> populateReply(ProductsDetailsDto productsDetailsDto) {
        List<String> reply = new ArrayList<>();
        reply.add(null);//TODO need to be populated with links to products
        return reply;
    }

    private Map<String, String> populateParams(ProductsDetailsDto productsDetailsDto) {
        Map<String, String> params = new HashMap<>();
        List<ProductDetailsDto> productDetailsDtos = productsDetailsDto.getProductsDetailsDto();
        for(int i=0; i<productDetailsDtos.size(); i++){
            params.put(SIMILAR_PRODUCT_PRICE_ENTITY + i, Double.toString(productDetailsDtos.get(i).getPrice()));
            params.put(SIMILAR_PRODUCT_SUPPLY_TIME_ENTITY + i, productDetailsDtos.get(i).getDeliveryDetails());
            params.put(SIMILAR_PRODUCT_PRODUCT_DETAILS_ENTITY + i, productDetailsDtos.get(i).getProductName());
            if(IS_IN_STOCK == productDetailsDtos.get(i).getIsInStock()){
                params.put(SIMILAR_PRODUCT_STOCK_ENTITY + i, "true");
            }
            else{
                params.put(SIMILAR_PRODUCT_STOCK_ENTITY + i, "false");
            }
        }
        return params;
    }
}
