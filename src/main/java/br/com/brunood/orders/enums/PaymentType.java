package br.com.brunood.orders.enums;

import lombok.Getter;

@Getter
public enum PaymentType {

    PIX("pix"),
    INVOICE("invoice"),
    CREDIT_CARD("credit_card");
    private final String value;

    PaymentType(String value) {
        this.value = value;
    }
}
