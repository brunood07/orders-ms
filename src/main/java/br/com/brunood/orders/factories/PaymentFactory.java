package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreateOrderPaymentInfoDTO;
import br.com.brunood.orders.dtos.producer.CreatePaymentDTO;

import java.math.BigDecimal;

public class PaymentFactory {

    public static CreatePaymentDTO paymentMessage(Long orderId, CreateOrderPaymentInfoDTO data, BigDecimal total) {

        return CreatePaymentDTO.builder()
                .cardInfo(data.getCardInfo())
                .clientDocument(data.getClientDocument())
                .installments(data.getInstallments())
                .orderId(orderId)
                .paymentType(data.getPaymentType())
                .value(total)
                .build();
    }
}
