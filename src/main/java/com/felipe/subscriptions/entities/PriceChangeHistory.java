package com.felipe.subscriptions.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
public class PriceChangeHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Subscription subscription;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal oldPrice;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal newPrice;

    @Column(nullable = false)
    private LocalDateTime changedAt;
}
