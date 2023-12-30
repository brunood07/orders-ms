package br.com.brunood.orders.dtos;


import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderUseCaseRequestDTO {

    CreateOrderDTO order;
    CreateOrderPaymentInfoDTO paymentInfo;
    List<CreateOrderProductDTO> products;
    CreatePriceInfoDTO priceInfo;
    CreateOrderAddressDTO addressInfo;
}
