package com.felipe.subscriptions.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
public class PaymentMethod {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Column(length = 40)
    private String brand; //Visa, Master, etc.

    @Column(length = 4)
    private String last4; // Últimos 4 dígitos;

    private Integer expiryMonth; // 1..12
    private Integer expiryYear; // yyyy

}
