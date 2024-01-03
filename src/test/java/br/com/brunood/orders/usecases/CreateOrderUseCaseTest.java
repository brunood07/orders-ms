package br.com.brunood.orders.usecases;

import br.com.brunood.orders.dtos.CreateOrderUseCaseRequestDTO;
import br.com.brunood.orders.factories.*;
import br.com.brunood.orders.repositories.*;
import br.com.brunood.orders.usecases.clients.PaymentsMsClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CreateOrderUseCaseTest {

    @Mock
    OrdersRepository ordersRepository;
    @Mock
    PaymentsMsClient paymentsMsClient;
    @Mock
    PriceInfoRepository priceInfoRepository;
    @Mock
    OrderProductsRepository orderProductsRepository;
    @Mock
    OrderAddressRepository orderAddressRepository;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @InjectMocks
    CreateOrderUseCase createOrderUseCase;

    @Test
    void shouldRegisterNewOrderWithValidBody() {
        when(ordersRepository.save(any())).thenReturn(OrderFactoryTest.createOrder());
        when(priceInfoRepository.save(any())).thenReturn(PriceInfoFactoryTest.createPriceInfo());
        when(orderProductsRepository.saveAll(any())).thenReturn(ProductsFactoryTest.createOrderProducts());
        when(orderAddressRepository.save(any())).thenReturn(AddressFactoryTest.createAddress());

        createOrderUseCase.execute(createOrderPayload());

        verify(ordersRepository, times(1)).save(any());
        verify(priceInfoRepository, times(1)).save(any());
        verify(orderProductsRepository, times(1)).saveAll(any());
        verify(orderAddressRepository, times(1)).save(any());
    }

    public CreateOrderUseCaseRequestDTO createOrderPayload() {

        return CreateOrderUseCaseRequestDTO.builder()
                .addressInfo(AddressFactoryTest.createAddressPayload())
                .order(OrderFactoryTest.createOrderPayload())
                .paymentInfo(PaymentInfoFactoryTest.createPaymentInfoWithCreditCardPayload())
                .priceInfo(PriceInfoFactoryTest.createPriceInfoPayload())
                .products(ProductsFactoryTest.createOrderProductsPayload())
                .build();
    }
}
