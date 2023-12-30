package br.com.brunood.orders.repositories;

import br.com.brunood.orders.entities.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {

    List<OrderProducts> findByOrderId(Long orderId);
}
