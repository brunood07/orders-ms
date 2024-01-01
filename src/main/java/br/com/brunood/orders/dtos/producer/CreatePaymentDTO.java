package br.com.brunood.orders.dtos.producer;

import br.com.brunood.orders.dtos.CreditCardDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreatePaymentDTO {

    private CreditCardDTO cardInfo;

    @NotNull(message = "Payment type missing")
    @NotBlank(message = "Payment type missing")
    private String paymentType;
    @NotNull(message = "Client document missing")
    @NotBlank(message = "Client document missing")
    private String clientDocument;
    @NotNull(message = "Installments missing")
    private int installments;
    private Long orderId;
    private BigDecimal value;
}
