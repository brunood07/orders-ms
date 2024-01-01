package br.com.brunood.orders.usecases;

import br.com.brunood.orders.dtos.CreateOrderUseCaseRequestDTO;
import br.com.brunood.orders.dtos.CreateOrderUseCaseResponseDTO;
import br.com.brunood.orders.dtos.producer.CreatePaymentDTO;
import br.com.brunood.orders.entities.*;
import br.com.brunood.orders.enums.OrderStatus;
import br.com.brunood.orders.enums.PaymentStatus;
import br.com.brunood.orders.enums.PaymentType;
import br.com.brunood.orders.exceptions.EmptyBodyException;
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
    private OrderPaymentInfoRepository orderPaymentInfoRepository;
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

        var orderToCreate = Orders.builder()
                .clientId(data.getOrder().getClientId())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(OrderStatus.CREATED.getValue())
                .build();

        var order = this.ordersRepository.save(orderToCreate);
        var orderId = order.getOrderId();

        var currentPaymentInfo = OrderPaymentInfo.builder()
                .clientDocument(data.getPaymentInfo().getClientDocument())
                .installments(data.getPaymentInfo().getInstallments())
                .orderId(orderId)
                .paymentStatus(PaymentStatus.PENDING.getValue())
                .paymentType(data.getPaymentInfo().getPaymentType())
                .build();

        var paymentInfo = this.orderPaymentInfoRepository.save(currentPaymentInfo);

        var total = data.getPriceInfo().getSubTotal().add(data.getPriceInfo().getShipping()).add(data.getPriceInfo().getTax()).subtract(data.getPriceInfo().getDiscount());

        var currentPriceInfo = PriceInfo.builder()
                .discount(data.getPriceInfo().getDiscount())
                .orderId(orderId)
                .shipping(data.getPriceInfo().getShipping())
                .subTotal(data.getPriceInfo().getSubTotal())
                .tax(data.getPriceInfo().getTax())
                .total(total)
                .build();

        var priceInfo = this.priceInfoRepository.save(currentPriceInfo);

        List<OrderProducts> orderProducts = new ArrayList<>();
        data.getProducts().forEach(product -> {
            orderProducts.add(OrderProducts.builder().idProduct(product.getIdProduct()).price(product.getPrice()).quantity(product.getQuantity()).orderId(orderId).build());
        });

        var products = this.orderProductsRepository.saveAll(orderProducts);

        var orderAddress = OrderAddress.builder()
                .city(data.getAddressInfo().getCity())
                .complement(data.getAddressInfo().getComplement())
                .neighborhood(data.getAddressInfo().getNeighboorhood())
                .number(data.getAddressInfo().getNumber())
                .orderId(orderId)
                .postalCode(data.getAddressInfo().getPostalCode())
                .state(data.getAddressInfo().getState())
                .street(data.getAddressInfo().getStreet())
                .build();

        var address = this.orderAddressRepository.save(orderAddress);

        var message = CreatePaymentDTO.builder()
                .cardInfo(data.getPaymentInfo().getCardInfo())
                .clientDocument(data.getPaymentInfo().getClientDocument())
                .installments(data.getPaymentInfo().getInstallments())
                .orderId(orderId)
                .paymentType(data.getPaymentInfo().getPaymentType())
                .value(total)
                .build();

        rabbitTemplate.convertAndSend("payment.created", message);

        return CreateOrderUseCaseResponseDTO.builder()
                .address(address)
                .order(order)
                .paymentInfo(paymentInfo)
                .priceInfo(priceInfo)
                .products(products)
                .build();
    }

}
