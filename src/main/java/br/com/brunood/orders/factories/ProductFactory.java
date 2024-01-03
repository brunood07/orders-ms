package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreateOrderProductDTO;
import br.com.brunood.orders.entities.OrderProducts;

public class ProductFactory {

    public static OrderProducts createProduct(Long orderId, CreateOrderProductDTO data) {
        return OrderProducts.builder()
                .idProduct(data.getIdProduct())
                .price(data.getPrice())
                .quantity(data.getQuantity())
                .orderId(orderId)
                .build();
    }
}
