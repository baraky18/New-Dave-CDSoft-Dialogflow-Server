package com.cdsoft.dialogflowserver.components;

import com.cdsoft.dialogflowserver.dtos.*;
import com.cdsoft.dialogflowserver.entities.ProductDetails;
import com.cdsoft.dialogflowserver.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.cdsoft.dialogflowserver.util.Constants.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReplyManager {

    private final ProductService productService;

    public WebhookResponseDto handleProductDetailsRequest(WebhookRequestDto webhookRequestDto) {
        String productName = getProductNameFromRequest(webhookRequestDto);
        ProductDetails productDetails = productService.getProductDetails(productName);
        return prepareWebhookResponse(productDetails);
    }

    private String getProductNameFromRequest(WebhookRequestDto webhookRequestDto) {
        String productNameWithStars = webhookRequestDto.getText().replace(UNWANTED_TEXT_FOR_PRODUCT_DETAILS_MSG, "");
        String productName = productNameWithStars.replace("*", "");
        log.info("product name is: \n" + productName);
        return productName;
    }

    private WebhookResponseDto prepareWebhookResponse(ProductDetails productDetails) {
        log.info("ProductDetailsService.prepareWebhookResponseWithProductDescription");
        ArrayList<String> reply = new ArrayList<>();
        if(IS_IN_STOCK.equalsIgnoreCase(productDetails.getIsInStock())){
            reply.add(productDetails.getProductShortDescription());
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
}