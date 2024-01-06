package br.com.brunood.orders.usecases;

import br.com.brunood.orders.enums.ShippingStatus;
import br.com.brunood.orders.exceptions.ShippingNotFound;
import br.com.brunood.orders.repositories.OrderAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateShippingStatusUseCase {

    @Autowired
    private OrderAddressRepository orderAddressRepository;

    public void execute(Long orderId, ShippingStatus status) {
        var orderAddressExists = this.orderAddressRepository.findByOrderId(orderId).orElseThrow(ShippingNotFound::new);

        orderAddressExists.setStatus(status.getValue());

        this.orderAddressRepository.save(orderAddressExists);
    }
}
