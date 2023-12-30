package br.com.brunood.orders.repositories;

import br.com.brunood.orders.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByClientId(Long clientId);
}
