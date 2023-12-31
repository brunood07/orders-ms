package br.com.brunood.orders.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderPaymentInfoDTO {

    private CreditCardDTO cardInfo;

    @NotNull(message = "Payment type missing")
    @NotBlank(message = "Payment type missing")
    private String paymentType;
    @NotNull(message = "Client document missing")
    @NotBlank(message = "Client document missing")
    private String clientDocument;
    @NotNull(message = "Installments missing")
    private int installments;
}
