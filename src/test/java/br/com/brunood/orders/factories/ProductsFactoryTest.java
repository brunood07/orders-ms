package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreateOrderProductDTO;
import br.com.brunood.orders.entities.OrderProducts;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsFactoryTest {

    public static List<OrderProducts> createOrderProducts() {
        List<OrderProducts> products = new ArrayList<>();

        products.add(OrderProducts.builder().orderId(1L).idOrderProduct(1L).idProduct(1L).price(new BigDecimal(450)).quantity(1).build());
        products.add(OrderProducts.builder().orderId(1L).idOrderProduct(2L).idProduct(2L).price(new BigDecimal(500)).quantity(1).build());

        return products;
    }

    public static List<CreateOrderProductDTO> createOrderProductsPayload() {
        List<CreateOrderProductDTO> products = new ArrayList<>();

        products.add(CreateOrderProductDTO.builder().idProduct(1L).price(new BigDecimal(450)).quantity(1).build());
        products.add(CreateOrderProductDTO.builder().idProduct(2L).price(new BigDecimal(500)).quantity(1).build());

        return products;
    }
}
