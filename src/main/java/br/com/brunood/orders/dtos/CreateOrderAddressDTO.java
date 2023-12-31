package br.com.brunood.orders.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderAddressDTO {

    @NotBlank(message = "Street missing")
    private String street;
    @NotBlank(message = "Number missing")
    private String number;
    @NotBlank(message = "City missing")
    private String city;
    @NotBlank(message = "State missing")
    private String state;
    @NotBlank(message = "Neighboorhood missing")
    private String neighboorhood;
    private String complement;
    @NotBlank(message = "Postal Code missing")
    private String postalCode;
}
