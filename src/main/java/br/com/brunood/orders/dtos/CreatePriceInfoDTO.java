package br.com.brunood.orders.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreatePriceInfoDTO {

    private BigDecimal discount;
    @NotNull(message = "sub total missing")
    private BigDecimal subTotal;
    private BigDecimal shipping;
    private BigDecimal tax;
}
