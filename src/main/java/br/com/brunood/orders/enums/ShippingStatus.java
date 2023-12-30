package br.com.brunood.orders.enums;

import lombok.Getter;

@Getter
public enum ShippingStatus {

    PENDING("Pending"),
    PROCESSING("Processing"),
    FAILED("Failed"),
    FINISHED("Finished");

    private final String value;

    ShippingStatus(String value) {
        this.value = value;
    }
}
