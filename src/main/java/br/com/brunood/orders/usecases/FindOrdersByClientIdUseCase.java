package br.com.brunood.orders.usecases;

import br.com.brunood.orders.dtos.GetOrderByOrderIdUseCaseDTO;
import br.com.brunood.orders.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindOrdersByClientIdUseCase {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderPaymentInfoRepository orderPaymentInfoRepository;
    @Autowired
    private PriceInfoRepository priceInfoRepository;
    @Autowired
    private OrderProductsRepository orderProductsRepository;
    @Autowired
    private OrderAddressRepository orderAddressRepository;

    public List<GetOrderByOrderIdUseCaseDTO> execute(Long clientId) {

        var clientOrders = this.ordersRepository.findByClientId(clientId);

        if (clientOrders.isEmpty()) new ArrayList<>();

        List<GetOrderByOrderIdUseCaseDTO> orders = new ArrayList<>();

        clientOrders.forEach(order -> {
            var orderId = order.getOrderId();
            var address = this.orderAddressRepository.findByOrderId(orderId).orElse(null);
            var priceInfo = this.priceInfoRepository.findByOrderId(orderId).orElse(null);
            var paymentInfo = this.orderPaymentInfoRepository.findByOrderId(orderId).orElse(null);
            var products = this.orderProductsRepository.findByOrderId(orderId);

            orders.add(GetOrderByOrderIdUseCaseDTO.builder()
                    .address(address)
                    .order(order)
                    .paymentInfo(paymentInfo)
                    .priceInfo(priceInfo)
                    .products(products)
                    .build());
        });

        return orders;
    }
}
