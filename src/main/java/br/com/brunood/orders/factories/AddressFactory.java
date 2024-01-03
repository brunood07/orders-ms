package br.com.brunood.orders.factories;

import br.com.brunood.orders.dtos.CreateOrderAddressDTO;
import br.com.brunood.orders.entities.OrderAddress;

public class AddressFactory {

    public static OrderAddress createAddress(Long orderId, CreateOrderAddressDTO data) {

        return  OrderAddress.builder()
                .city(data.getCity())
                .complement(data.getComplement())
                .neighborhood(data.getNeighboorhood())
                .number(data.getNumber())
                .orderId(orderId)
                .postalCode(data.getPostalCode())
                .state(data.getState())
                .street(data.getStreet())
                .build();
    }
}
