package br.com.brunood.orders.usecases;

import br.com.brunood.orders.exceptions.OrderNotFoundException;
import br.com.brunood.orders.factories.*;
import br.com.brunood.orders.repositories.*;
import br.com.brunood.orders.usecases.clients.PaymentsMsClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GetOrderByOrderIdUseCaseTest {

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
    @InjectMocks
    GetOrderByOrderIdUseCase getOrderByOrderIdUseCase;

    @Test
    void shouldReturnNullIfOrderDontExists() {
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> getOrderByOrderIdUseCase.execute(1L));

        verify(ordersRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldReturnOrderForOrderId() {
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.of(OrderFactoryTest.createOrder()));
        when(paymentsMsClient.getPaymentByOrderId(anyString())).thenReturn(PaymentInfoFactoryTest.createPaymentDto());
        when(priceInfoRepository.findByOrderId(anyLong())).thenReturn(Optional.of(PriceInfoFactoryTest.createPriceInfo()));
        when(orderProductsRepository.findByOrderId(anyLong())).thenReturn(ProductsFactoryTest.createOrderProducts());
        when(orderAddressRepository.findByOrderId(anyLong())).thenReturn(Optional.of(AddressFactoryTest.createAddress()));

        var order = getOrderByOrderIdUseCase.execute(1L);

        verify(ordersRepository, times(1)).findById(anyLong());
        verify(paymentsMsClient, times(1)).getPaymentByOrderId(anyString());
        verify(priceInfoRepository, times(1)).findByOrderId(anyLong());
        verify(orderProductsRepository, times(1)).findByOrderId(anyLong());
        verify(orderAddressRepository, times(1)).findByOrderId(anyLong());

        assertEquals(1L, order.getOrder().getOrderId());
    }
}
