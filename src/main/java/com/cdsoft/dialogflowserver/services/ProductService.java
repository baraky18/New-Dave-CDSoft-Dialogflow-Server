package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.google.*;
import com.cdsoft.dialogflowserver.dtos.integrator.ProductDetailsDto;
import com.cdsoft.dialogflowserver.dtos.integrator.ProductRequestDto;
import com.cdsoft.dialogflowserver.mappers.language.CategoryMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static com.cdsoft.dialogflowserver.util.DialogflowEntities.PRODUCT_DETAILS_ENTITY;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final RestTemplate integratorRestTemplate;
    private final CategoryMapper categoryMapper;
    private final DialogflowWebhookResponseService dialogflowWebhookResponseService;
    private final ObjectMapper objectMapper;

    public WebhookResponseDto getProductDetailsAsWebhookResponse(WebhookRequestDto webhookRequestDto) throws JsonProcessingException {
        log.info("ProductService.getProductDetailsAsWebhookResponse");
        return prepareWebhookResponse(getProductDetails(webhookRequestDto));
    }

    public ProductDetailsDto getProductDetails(WebhookRequestDto webhookRequestDto) {
        log.info("ProductService.getProductDetails");
        String productName = getProductNameFromRequest(webhookRequestDto);
        ProductRequestDto productRequestDto = ProductRequestDto.builder().productName(productName).build();
        return integratorRestTemplate.postForObject("/product/name", productRequestDto, ProductDetailsDto.class);
    }

    private String getProductNameFromRequest(WebhookRequestDto webhookRequestDto) {
        if(webhookRequestDto.getSessionInfo().getParameters().get(PRODUCT_DETAILS_ENTITY) == null){
            String productNameWithStars = webhookRequestDto.getText().replace(UNWANTED_TEXT_FOR_PRODUCT_DETAILS_MSG, "");
            String productName = productNameWithStars.replace("*", "");
            log.info("product name is: \n" + productName);
            return productName;
        }
        return webhookRequestDto.getSessionInfo().getParameters().get(PRODUCT_DETAILS_ENTITY);
    }

    private WebhookResponseDto prepareWebhookResponse(ProductDetailsDto productDetailsDto) throws JsonProcessingException {
        log.info("ProductService.prepareWebhookResponse");
        List<String> reply = populateReply(productDetailsDto);
        Map<String, String> params = populateParams(productDetailsDto);
        return dialogflowWebhookResponseService.populateWebhookResponseDto(reply, params);
    }

    private List<String> populateReply(ProductDetailsDto productDetailsDto) {
        List<String> reply = new ArrayList<>();
        if(IS_IN_STOCK == productDetailsDto.getIsInStock()){
            reply.add(inStockMsg(productDetailsDto));
        }
        else{
            reply.add(NOT_IN_STOCK_MSG);
        }
        return reply;
    }

    private Map<String, String> populateParams(ProductDetailsDto productDetailsDto) throws JsonProcessingException {
        Map<String, String> params = new HashMap<>();
        params.put(PRICE_ENTITY, Double.toString(productDetailsDto.getPrice()));
        params.put(SUPPLY_TIME_ENTITY, productDetailsDto.getDeliveryDetails());
        params.put(PRODUCT_DETAILS_ENTITY, productDetailsDto.getProductName());
        params.put(PRODUCT_FEATURES_ENTITY, objectMapper.writeValueAsString(productDetailsDto.getFeaturesValues()));
        if(IS_IN_STOCK == productDetailsDto.getIsInStock()){
            params.put(STOCK_ENTITY, "true");
        }
        else{
            params.put(STOCK_ENTITY, "false");
        }
        return params;
    }

    private String inStockMsg(ProductDetailsDto productDetailsDto) {
        return String.format(IN_STOCK_MSG,
                categoryMapper.map(productDetailsDto.getProductCategoryDetails().getCategoryName()),
                productDetailsDto.getManufacturer().getManufacturerName());
    }
}
