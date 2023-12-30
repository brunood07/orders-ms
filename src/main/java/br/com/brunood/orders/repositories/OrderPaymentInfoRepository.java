package br.com.brunood.orders.repositories;

import br.com.brunood.orders.entities.OrderPaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderPaymentInfoRepository extends JpaRepository<OrderPaymentInfo, Long> {

    Optional<OrderPaymentInfo> findByOrderId(Long orderId);
}
