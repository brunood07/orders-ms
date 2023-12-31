package br.com.brunood.orders.usecases;

import br.com.brunood.orders.factories.*;
import br.com.brunood.orders.repositories.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GetOrderByOrderIdUseCaseTest {

    @Mock
    OrdersRepository ordersRepository;
    @Mock
    OrderPaymentInfoRepository orderPaymentInfoRepository;
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

        var order = getOrderByOrderIdUseCase.execute(1L);

        verify(ordersRepository, times(1)).findById(anyLong());
        assertNull(order);
    }

    @Test
    void shouldReturnOrderForOrderId() {
        when(ordersRepository.findById(anyLong())).thenReturn(Optional.of(OrderFactory.createOrder()));
        when(orderPaymentInfoRepository.findByOrderId(anyLong())).thenReturn(Optional.of(PaymentInfoFactory.createPaymentInfoWithCreditCard()));
        when(priceInfoRepository.findByOrderId(anyLong())).thenReturn(Optional.of(PriceInfoFactory.createPriceInfo()));
        when(orderProductsRepository.findByOrderId(anyLong())).thenReturn(ProductsFactory.createOrderProducts());
        when(orderAddressRepository.findByOrderId(anyLong())).thenReturn(Optional.of(AddressFactory.createAddress()));

        var order = getOrderByOrderIdUseCase.execute(1L);

        verify(ordersRepository, times(1)).findById(anyLong());
        verify(orderPaymentInfoRepository, times(1)).findByOrderId(anyLong());
        verify(priceInfoRepository, times(1)).findByOrderId(anyLong());
        verify(orderProductsRepository, times(1)).findByOrderId(anyLong());
        verify(orderAddressRepository, times(1)).findByOrderId(anyLong());

        assertEquals(1L, order.getOrder().getOrderId());
    }
}
