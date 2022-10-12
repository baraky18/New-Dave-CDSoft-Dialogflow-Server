package com.cdsoft.dialogflowserver.model;

import com.cdsoft.dialogflowserver.enums.BusinessNotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessNotification {

    private String productDetails;
    private String customerDetails;
    private LocalDateTime requestedDateTime;
    private BusinessNotificationType businessNotificationType;

    @Override
    public String toString(){
        return  "Notification is: " + businessNotificationType.toString() + "\n" +
                "Product details are: " + productDetails + "\n" +
                "Customer details are: " + customerDetails + "\n" +
                "Requested Date time is: " + requestedDateTime;
    }

}
