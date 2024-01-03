package br.com.brunood.orders.usecases;

import br.com.brunood.orders.dtos.GetOrderByOrderIdUseCaseDTO;
import br.com.brunood.orders.repositories.*;
import br.com.brunood.orders.usecases.clients.PaymentsMsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOrderByOrderIdUseCase {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private PriceInfoRepository priceInfoRepository;
    @Autowired
    private OrderProductsRepository orderProductsRepository;
    @Autowired
    private OrderAddressRepository orderAddressRepository;
    @Autowired
    private PaymentsMsClient paymentsMsClient;

    public GetOrderByOrderIdUseCaseDTO execute(Long orderId) {

        var order = this.ordersRepository.findById(orderId).orElse(null);

        if (order == null) return null;

        var address = this.orderAddressRepository.findByOrderId(orderId).orElse(null);
        var priceInfo = this.priceInfoRepository.findByOrderId(orderId).orElse(null);
        var products = this.orderProductsRepository.findByOrderId(orderId);
        var payment = this.paymentsMsClient.getPaymentByOrderId(String.valueOf(orderId));

        return GetOrderByOrderIdUseCaseDTO.builder()
                .address(address)
                .order(order)
                .priceInfo(priceInfo)
                .payment(payment)
                .products(products)
                .build();
    }
}
