package br.com.brunood.orders.usecases;

import br.com.brunood.orders.dtos.CreateOrderUseCaseRequestDTO;
import br.com.brunood.orders.dtos.CreateOrderUseCaseResponseDTO;
import br.com.brunood.orders.dtos.producer.CreatePaymentDTO;
import br.com.brunood.orders.entities.*;
import br.com.brunood.orders.enums.OrderStatus;
import br.com.brunood.orders.enums.PaymentStatus;
import br.com.brunood.orders.enums.PaymentType;
import br.com.brunood.orders.exceptions.EmptyBodyException;
import br.com.brunood.orders.factories.*;
import br.com.brunood.orders.repositories.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateOrderUseCase {

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private PriceInfoRepository priceInfoRepository;
    @Autowired
    private OrderProductsRepository orderProductsRepository;
    @Autowired
    private OrderAddressRepository orderAddressRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public CreateOrderUseCaseResponseDTO execute(CreateOrderUseCaseRequestDTO data) {
        if (ObjectUtils.isEmpty(data)) throw new EmptyBodyException();
        if (data.getPaymentInfo().getPaymentType().equals(PaymentType.CREDIT_CARD.getValue()) && ObjectUtils.isEmpty(data.getPaymentInfo().getCardInfo())) throw new EmptyBodyException();

        var orderToCreate = OrderFactory.createOrder(data.getOrder().getClientId());
        var order = this.ordersRepository.save(orderToCreate);
        var orderId = order.getOrderId();

        var total = data.getPriceInfo().getSubTotal().add(data.getPriceInfo().getShipping()).add(data.getPriceInfo().getTax()).subtract(data.getPriceInfo().getDiscount());
        var currentPriceInfo = PriceInfoFactory.createPriceInfo(orderId, data.getPriceInfo(), total);
        var priceInfo = this.priceInfoRepository.save(currentPriceInfo);

        List<OrderProducts> orderProducts = new ArrayList<>();
        data.getProducts().forEach(product -> {
            orderProducts.add(ProductFactory.createProduct(orderId, product));
        });
        var products = this.orderProductsRepository.saveAll(orderProducts);

        var orderAddress = AddressFactory.createAddress(orderId, data.getAddressInfo());
        var address = this.orderAddressRepository.save(orderAddress);

        var paymentMessage = PaymentFactory.paymentMessage(orderId, data.getPaymentInfo(), total);
        rabbitTemplate.convertAndSend("payment.created", paymentMessage);

        return CreateOrderUseCaseResponseDTO.builder()
                .address(address)
                .order(order)
                .priceInfo(priceInfo)
                .products(products)
                .build();
    }

}
