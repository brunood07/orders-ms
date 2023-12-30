package br.com.brunood.orders.repositories;

import br.com.brunood.orders.entities.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderAddressRepository extends JpaRepository<OrderAddress, Long> {

    Optional<OrderAddress> findByOrderId(Long orderId);
}
