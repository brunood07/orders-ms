package br.com.brunood.orders.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_order_address")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_address")
    private Long idOrderAddress;

    @Column(name = "order_id")
    private Long orderId;

    private String street;

    private String number;

    private String city;

    private String state;

    private String neighboorhood;

    private String complement;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "days_to_delivery")
    private int daysToDelivery;
}
