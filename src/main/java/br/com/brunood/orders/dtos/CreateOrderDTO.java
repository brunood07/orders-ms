package br.com.brunood.orders.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderDTO {

    private Long clientId;
}
