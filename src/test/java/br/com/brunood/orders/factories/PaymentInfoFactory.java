package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreateOrderPaymentInfoDTO;
import br.com.brunood.orders.dtos.CreditCardDTO;
import br.com.brunood.orders.entities.OrderPaymentInfo;
import br.com.brunood.orders.enums.PaymentStatus;
import br.com.brunood.orders.enums.PaymentType;
import org.springframework.stereotype.Service;

@Service
public class PaymentInfoFactory {

    public static OrderPaymentInfo createPaymentInfoWithCreditCard() {

        return OrderPaymentInfo.builder()
                .cardCvv("123")
                .cardExpirationDate("11/30")
                .cardName("Test Name")
                .cardNumber("123456789")
                .clientDocument("11111111111")
                .idOrderPaymentInfo(1L)
                .idPayment(1L)
                .installments(1)
                .paymentStatus(PaymentStatus.PENDING.getValue())
                .paymentType(PaymentType.CREDIT_CARD.getValue())
                .build();
    }

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
}
