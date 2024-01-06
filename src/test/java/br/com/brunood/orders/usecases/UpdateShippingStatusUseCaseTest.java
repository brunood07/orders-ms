package br.com.brunood.orders.usecases;

import br.com.brunood.orders.enums.ShippingStatus;
import br.com.brunood.orders.exceptions.ShippingNotFound;
import br.com.brunood.orders.factories.AddressFactoryTest;
import br.com.brunood.orders.repositories.OrderAddressRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UpdateShippingStatusUseCaseTest {

    @Mock
    OrderAddressRepository orderAddressRepository;
    @InjectMocks
    UpdateShippingStatusUseCase updateShippingStatusUseCase;

    @Test
    void shouldBeAbleToUpdateAShippingStatus() {
        when(orderAddressRepository.findByOrderId(anyLong())).thenReturn(Optional.of(AddressFactoryTest.createAddress()));
        when(orderAddressRepository.save(any())).thenReturn(AddressFactoryTest.createAddress());

        updateShippingStatusUseCase.execute(1L, ShippingStatus.PROCESSING);

        verify(orderAddressRepository, times(1)).findByOrderId(anyLong());
        verify(orderAddressRepository, times(1)).save(any());
    }

    @Test
    void shouldNotBeAbleToUpdateAStatusOfAOrderThatNotExists() {
        when(orderAddressRepository.findByOrderId(anyLong())).thenReturn(Optional.empty());

        assertThrows(ShippingNotFound.class, () -> updateShippingStatusUseCase.execute(1L, ShippingStatus.PROCESSING));

        verify(orderAddressRepository, times(1)).findByOrderId(anyLong());
    }

}
