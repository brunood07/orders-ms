package br.com.brunood.orders.usecases;

import br.com.brunood.orders.dtos.CreateOrderUseCaseRequestDTO;
import br.com.brunood.orders.exceptions.EmptyBodyException;
import br.com.brunood.orders.factories.*;
import br.com.brunood.orders.repositories.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CreateOrderUseCaseTest {

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
    CreateOrderUseCase createOrderUseCase;

    @Test
    void shouldRegisterNewOrderWithInvalidBody() {
        assertThrows(EmptyBodyException.class, () -> createOrderUseCase.execute(null));
    }

    @Test
    void shouldRegisterNewOrderCreditCardAndCardInfoEmpty() {
        assertThrows(EmptyBodyException.class, () -> createOrderUseCase.execute(createOrderInvalidPayload()));
    }

    @Test
    void shouldRegisterNewOrderWithValidBody() {
        when(ordersRepository.save(any())).thenReturn(OrderFactory.createOrder());
        when(orderPaymentInfoRepository.save(any())).thenReturn(PaymentInfoFactory.createPaymentInfoWithCreditCard());
        when(priceInfoRepository.save(any())).thenReturn(PriceInfoFactory.createPriceInfo());
        when(orderProductsRepository.saveAll(any())).thenReturn(ProductsFactory.createOrderProducts());
        when(orderAddressRepository.save(any())).thenReturn(AddressFactory.createAddress());

        createOrderUseCase.execute(createOrderPayload());

        verify(ordersRepository, times(1)).save(any());
        verify(orderPaymentInfoRepository, times(1)).save(any());
        verify(priceInfoRepository, times(1)).save(any());
        verify(orderProductsRepository, times(1)).saveAll(any());
        verify(orderAddressRepository, times(1)).save(any());
    }

    public CreateOrderUseCaseRequestDTO createOrderPayload() {

        return CreateOrderUseCaseRequestDTO.builder()
                .addressInfo(AddressFactory.createAddressPayload())
                .order(OrderFactory.createOrderPayload())
                .paymentInfo(PaymentInfoFactory.createPaymentInfoWithCreditCardPayload())
                .priceInfo(PriceInfoFactory.createPriceInfoPayload())
                .products(ProductsFactory.createOrderProductsPayload())
                .build();
    }

    public CreateOrderUseCaseRequestDTO createOrderInvalidPayload() {

        return CreateOrderUseCaseRequestDTO.builder()
                .addressInfo(AddressFactory.createAddressPayload())
                .order(OrderFactory.createOrderPayload())
                .paymentInfo(PaymentInfoFactory.createPaymentInfoForCreditCardWithCardInfoEmpty())
                .priceInfo(PriceInfoFactory.createPriceInfoPayload())
                .products(ProductsFactory.createOrderProductsPayload())
                .build();
    }
}
