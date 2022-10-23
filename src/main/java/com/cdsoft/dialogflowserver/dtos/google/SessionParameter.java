package com.cdsoft.dialogflowserver.dtos.google;

public enum SessionParameter {

    IS_IN_STOCK("is_in_stock"),
    PRODUCT_PRICE("product_price");

    public final String parameter;

    SessionParameter(String parameter) {
        this.parameter = parameter;
    }
}
