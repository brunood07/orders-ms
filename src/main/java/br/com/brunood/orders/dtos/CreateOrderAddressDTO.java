package br.com.brunood.orders.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderAddressDTO {

    private String street;
    private String number;
    private String city;
    private String state;
    private String neighboorhood;
    private String complement;
    private String postalCode;
}
