package br.com.brunood.orders.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "tb_order_products")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_product")
    private Long idOrderProduct;

    @Column(name = "order_id")
    private Long orderId;

    private int quantity;

    private BigDecimal price;

    @Column(name = "id_product")
    private Long idProduct;
}
