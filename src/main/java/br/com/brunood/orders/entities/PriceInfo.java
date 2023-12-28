package br.com.brunood.orders.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "tb_price_info")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_price_info")
    private Long idPriceInfo;

    @Column(name = "order_id")
    private Long orderId;

    private BigDecimal discount;

    private BigDecimal subTotal;

    private BigDecimal total;

    private BigDecimal shipping;

    private BigDecimal tax;
}
