package br.com.brunood.orders.dtos;


import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderUseCaseRequestDTO {

    @Valid
    CreateOrderDTO order;
    @Valid
    CreateOrderPaymentInfoDTO paymentInfo;
    @Valid
    List<CreateOrderProductDTO> products;
    @Valid
    CreatePriceInfoDTO priceInfo;
    @Valid
    CreateOrderAddressDTO addressInfo;
}
