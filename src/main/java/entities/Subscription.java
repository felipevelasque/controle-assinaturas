package entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = "id")
public class Subscription {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false, length = 80)
    private String serviceName; //Netflix, Spotify, etc.

    @Column(length = 80)
    private String planName; //Premium, Individual, etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private BillingCycle billingCycle;

    @Column(nullable = false)
    private LocalDate nextBillingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentMethod paymentMethod;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;
}

