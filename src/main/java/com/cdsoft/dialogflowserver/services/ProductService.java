package com.cdsoft.dialogflowserver.services;

import com.cdsoft.dialogflowserver.dtos.*;
import com.cdsoft.dialogflowserver.entities.ProductCategoryDetails;
import com.cdsoft.dialogflowserver.entities.ProductDetails;
import com.cdsoft.dialogflowserver.mappers.language.CategoryMapper;
import com.cdsoft.dialogflowserver.repositories.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static com.cdsoft.dialogflowserver.util.Constants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

//    private final ProductDetailsRepository productDetailsRepository;
    private final CategoryMapper categoryMapper;

    public WebhookResponseDto getProductDetails(WebhookRequestDto webhookRequestDto) {
        log.info("ProductService.getProductDetails");
        String productName = getProductNameFromRequest(webhookRequestDto);
//        Optional<ProductDetails> productDetailsOptional = productDetailsRepository.findByProductName(productName);
//        ProductDetails productDetails = productDetailsOptional.orElseGet(ProductDetails::new);
//        return prepareWebhookResponse(productDetails);
        return prepareWebhookResponse(new ProductDetails());
    }

    private String getProductNameFromRequest(WebhookRequestDto webhookRequestDto) {
        String productNameWithStars = webhookRequestDto.getText().replace(UNWANTED_TEXT_FOR_PRODUCT_DETAILS_MSG, "");
        String productName = productNameWithStars.replace("*", "");
        log.info("product name is: \n" + productName);
        return productName;
    }

    private WebhookResponseDto prepareWebhookResponse(ProductDetails productDetails) {
        log.info("ProductService.prepareWebhookResponse");
        ArrayList<String> reply = new ArrayList<>();
        if(IS_IN_STOCK.equalsIgnoreCase(productDetails.getIsInStock())){
            reply.add(inStockMsg(productDetails));
        }
        else{
            reply.add(NOT_IN_STOCK_MSG);
        }
        MessageDto message = MessageDto.builder()
                .text(TextDto.builder()
                        .text(reply).build()).build();
        ArrayList<MessageDto> messages = new ArrayList<>();
        messages.add(message);
        return WebhookResponseDto.builder()
                .fulfillmentResponseDto(FulfillmentResponseDto.builder()
                        .messages(messages).build()).build();
    }

    private String inStockMsg(ProductDetails productDetails) {
        Optional<ProductCategoryDetails> optionalCategory = productDetails.getProductCategoryDetails().stream().filter(p -> p.getProductCategoryParentId().intValue() == 2).findFirst();
        String categoryName = optionalCategory.isPresent() ? optionalCategory.get().getCategoryName() : PRODUCT;
        return String.format(IN_STOCK_MSG, categoryMapper.map(categoryName), productDetails.getManufacturer().getManufacturerName());
    }
}
