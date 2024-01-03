package br.com.brunood.orders.dtos.payments;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PaymentsDTO {

    private Long paymentId;
    private Long orderId;
    private BigDecimal value;
    private String paymentType;
    private String paymentStatus;
    private String description;
    private int installments;
    private String clientDocument;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
