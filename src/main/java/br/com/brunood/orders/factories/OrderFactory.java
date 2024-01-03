package br.com.brunood.orders.factories;

import br.com.brunood.orders.entities.Orders;
import br.com.brunood.orders.enums.OrderStatus;

import java.time.LocalDateTime;

public class OrderFactory {

    public static Orders createOrder(Long clientId) {
        return Orders.builder()
                .clientId(clientId)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(OrderStatus.CREATED.getValue())
                .build();
    }
}
