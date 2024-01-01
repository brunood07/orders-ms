package br.com.brunood.orders.usecases;

import br.com.brunood.orders.factories.*;
import br.com.brunood.orders.repositories.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FindOrdersByClientIdUseCaseTest {

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
    FindOrdersByClientIdUseCase findOrdersByClientIdUseCase;

    @Test
    void shouldReturnEmptyListIfClientHasNoOrders() {
        when(ordersRepository.findByClientId(anyLong())).thenReturn(new ArrayList<>());

        var orders = findOrdersByClientIdUseCase.execute(1L);

        verify(ordersRepository, times(1)).findByClientId(anyLong());
        assertEquals(0, orders.size());
    }

    @Test
    void shouldReturnAListOfOrdersIfClientHasOrders() {
        when(ordersRepository.findByClientId(anyLong())).thenReturn(OrderFactory.listOfOrders());
        when(orderAddressRepository.findByOrderId(anyLong())).thenReturn(Optional.of(AddressFactory.createAddress()));
        when(priceInfoRepository.findByOrderId(anyLong())).thenReturn(Optional.of(PriceInfoFactory.createPriceInfo()));
        when(orderPaymentInfoRepository.findByOrderId(anyLong())).thenReturn(Optional.of(PaymentInfoFactory.createPaymentInfoWithCreditCard()));
        when(orderProductsRepository.findByOrderId(anyLong())).thenReturn(ProductsFactory.createOrderProducts());

        var orders = findOrdersByClientIdUseCase.execute(1L);

        verify(ordersRepository, times(1)).findByClientId(anyLong());
        verify(orderPaymentInfoRepository, times(2)).findByOrderId(anyLong());
        verify(priceInfoRepository, times(2)).findByOrderId(anyLong());
        verify(orderProductsRepository, times(2)).findByOrderId(anyLong());
        verify(orderAddressRepository, times(2)).findByOrderId(anyLong());
        assertEquals(2, orders.size());
    }
}
