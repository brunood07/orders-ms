package br.com.brunood.orders.dtos;

import br.com.brunood.orders.entities.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetOrderByOrderIdUseCaseDTO {

    OrderAddress address;
    OrderPaymentInfo paymentInfo;
    List<OrderProducts> products;
    Orders order;
    PriceInfo priceInfo;
}
