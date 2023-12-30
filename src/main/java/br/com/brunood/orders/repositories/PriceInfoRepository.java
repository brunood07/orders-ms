package br.com.brunood.orders.repositories;

import br.com.brunood.orders.entities.PriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceInfoRepository extends JpaRepository<PriceInfo, Long> {

    Optional<PriceInfo> findByOrderId(Long orderId);
}
