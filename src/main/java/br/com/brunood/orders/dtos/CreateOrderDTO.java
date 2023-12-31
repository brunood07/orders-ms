package br.com.brunood.orders.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOrderDTO {

    @NotNull(message = "client id missing")
    private Long clientId;
}
