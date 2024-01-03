package br.com.brunood.orders.dtos;

import br.com.brunood.orders.entities.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderUseCaseResponseDTO {

    OrderAddress address;
    List<OrderProducts> products;
    Orders order;
    PriceInfo priceInfo;
}
