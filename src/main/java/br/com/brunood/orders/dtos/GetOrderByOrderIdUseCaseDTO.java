package br.com.brunood.orders.dtos;

import br.com.brunood.orders.dtos.payments.PaymentsDTO;
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
    List<OrderProducts> products;
    Orders order;
    PaymentsDTO payment;
    PriceInfo priceInfo;
}
