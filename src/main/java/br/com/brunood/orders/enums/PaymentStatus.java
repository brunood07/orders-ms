package br.com.brunood.orders.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {

    PENDING("Pending"),
    PROCESSING("Processing"),
    SETTLED("Settled"),
    FAILED("Failed");
    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }
}
