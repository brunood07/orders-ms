package br.com.brunood.orders.entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_order_payment_info")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_payment_info")
    private Long idOrderPaymentInfo;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "id_payment")
    private Long idPayment;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_cvv")
    private String cardCvv;

    @Column(name = "card_name")
    private String cardName;

    private int installments;

    @Column(name = "card_expiration_date")
    private String cardExpirationDate;

    @Column(name = "client_documento")
    private String clientDocument;
}
