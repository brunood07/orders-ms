package br.com.brunood.orders.usecases;

import br.com.brunood.orders.dtos.GetOrderByOrderIdUseCaseDTO;
import br.com.brunood.orders.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetOrderByOrderIdUseCase {

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

    public GetOrderByOrderIdUseCaseDTO execute(Long orderId) {

        var order = this.ordersRepository.findById(orderId).orElse(null);
        var address = this.orderAddressRepository.findByOrderId(orderId).orElse(null);
        var priceInfo = this.priceInfoRepository.findByOrderId(orderId).orElse(null);
        var paymentInfo = this.orderPaymentInfoRepository.findByOrderId(orderId).orElse(null);
        var products = this.orderProductsRepository.findByOrderId(orderId);

        return GetOrderByOrderIdUseCaseDTO.builder()
                .address(address)
                .order(order)
                .paymentInfo(paymentInfo)
                .priceInfo(priceInfo)
                .products(products)
                .build();
    }
}
