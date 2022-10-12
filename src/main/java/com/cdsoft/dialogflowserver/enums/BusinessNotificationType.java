package com.cdsoft.dialogflowserver.enums;

public enum BusinessNotificationType {
    PERFORM_BUSINESS_CALL("Perform business call");

    private final String businessNotificationType;

    BusinessNotificationType(String businessNotificationType) {
        this.businessNotificationType = businessNotificationType;
    }

    public String getBusinessNotificationTypeValue(){
        return businessNotificationType;
    }
}
