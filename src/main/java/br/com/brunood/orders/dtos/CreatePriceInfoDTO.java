package br.com.brunood.orders.dtos;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreatePriceInfoDTO {

    private BigDecimal discount;
    private BigDecimal subTotal;
    private BigDecimal shipping;
    private BigDecimal tax;
}
