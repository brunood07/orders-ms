package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreateOrderPaymentInfoDTO;
import br.com.brunood.orders.dtos.CreditCardDTO;
import br.com.brunood.orders.dtos.payments.PaymentsDTO;
import br.com.brunood.orders.enums.PaymentStatus;
import br.com.brunood.orders.enums.PaymentType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentInfoFactoryTest {

    public static CreateOrderPaymentInfoDTO createPaymentInfoWithCreditCardPayload() {
        var cardInfo = CreditCardDTO.builder()
                .cardCvv("123")
                .cardExpirationDate("11/30")
                .cardName("Test Name")
                .cardNumber("123456789")
                .build();

        return CreateOrderPaymentInfoDTO.builder()
                .cardInfo(cardInfo)
                .clientDocument("11111111111")
                .installments(1)
                .paymentType(PaymentType.CREDIT_CARD.getValue())
                .build();
    }

    public static CreateOrderPaymentInfoDTO createPaymentInfoForCreditCardWithCardInfoEmpty() {
        return CreateOrderPaymentInfoDTO.builder()
                .cardInfo(null)
                .clientDocument("11111111111")
                .installments(1)
                .paymentType(PaymentType.CREDIT_CARD.getValue())
                .build();
    }

    public static CreateOrderPaymentInfoDTO createPaymentInfoWithPix() {
        return CreateOrderPaymentInfoDTO.builder()
                .cardInfo(null)
                .clientDocument("11111111111")
                .installments(1)
                .paymentType(PaymentType.PIX.getValue())
                .build();
    }
    public static PaymentsDTO createPaymentDto() {

        return PaymentsDTO.builder()
                .clientDocument("11111111111")
                .createdAt(LocalDateTime.now())
                .description("Teste")
                .installments(1)
                .orderId(1L)
                .paymentId(1L)
                .paymentStatus(PaymentStatus.PROCESSING.getValue())
                .paymentType(PaymentType.CREDIT_CARD.getValue())
                .value(BigDecimal.TEN)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
