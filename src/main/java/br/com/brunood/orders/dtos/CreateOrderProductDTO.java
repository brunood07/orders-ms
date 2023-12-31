package br.com.brunood.orders.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderProductDTO {

    @NotNull(message = "quantity missing")
    private int quantity;
    @NotNull(message = "price missing")
    private BigDecimal price;
    @NotNull(message = "product id missing")
    private Long idProduct;
}
