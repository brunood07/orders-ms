package br.com.brunood.orders.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    CREATED("Created"),
    PROCESSING("Processing"),
    PENDING_PAYMENT("Pending payment"),
    PAYMENT_ERROR("Payment error"),
    FINISHED("Finished");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

}
