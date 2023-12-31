package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreateOrderDTO;
import br.com.brunood.orders.entities.Orders;
import br.com.brunood.orders.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderFactory {

    public static Orders createOrder() {
        return Orders.builder()
                .clientId(1L)
                .createdAt(LocalDateTime.now())
                .orderId(1L)
                .status(OrderStatus.CREATED.getValue())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static CreateOrderDTO createOrderPayload() {

        return CreateOrderDTO.builder()
                .clientId(1L)
                .build();
    }
}
