package br.com.brunood.orders.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderPaymentInfoDTO {

    private CreditCardDTO cardInfo;
    private String paymentType;
    private String clientDocument;
    private int installments;
}
