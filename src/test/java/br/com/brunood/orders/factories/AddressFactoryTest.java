package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreateOrderAddressDTO;
import br.com.brunood.orders.entities.OrderAddress;
import org.springframework.stereotype.Service;

@Service
public class AddressFactoryTest {

    public static OrderAddress createAddress() {

        return OrderAddress.builder()
                .city("City Test")
                .complement(null)
                .idOrderAddress(1L)
                .neighborhood("Neighboorhood Test")
                .number("13")
                .orderId(1L)
                .postalCode("12345678")
                .state("State Test")
                .street("Street Test")
                .build();
    }

    public static CreateOrderAddressDTO createAddressPayload() {

        return CreateOrderAddressDTO.builder()
                .city("City Test")
                .complement(null)
                .neighboorhood("Neighboorhood Test")
                .number("13")
                .postalCode("12345678")
                .state("State Test")
                .street("Street Test")
                .build();
    }
}
