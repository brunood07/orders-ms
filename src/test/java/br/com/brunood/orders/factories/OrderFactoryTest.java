package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreateOrderDTO;
import br.com.brunood.orders.entities.Orders;
import br.com.brunood.orders.enums.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderFactoryTest {

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

    public static List<Orders> listOfOrders() {
        List<Orders> orders = new ArrayList<>();

        orders.add(Orders.builder()
                .clientId(1L)
                .createdAt(LocalDateTime.now())
                .orderId(1L)
                .status(OrderStatus.CREATED.getValue())
                .updatedAt(LocalDateTime.now())
                .build());

        orders.add(Orders.builder()
                .clientId(1L)
                .createdAt(LocalDateTime.now())
                .orderId(2L)
                .status(OrderStatus.CREATED.getValue())
                .updatedAt(LocalDateTime.now())
                .build());

        return orders;
    }
}
