package br.com.brunood.orders.dtos;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderProductDTO {

    private int quantity;
    private BigDecimal price;
    private Long idProduct;
}
