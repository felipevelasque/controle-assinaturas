package com.felipe.subscriptions.repositories;

import com.felipe.subscriptions.entities.BillingCycle;
import com.felipe.subscriptions.entities.PaymentMethod;
import com.felipe.subscriptions.entities.PriceChangeHistory;
import com.felipe.subscriptions.entities.Subscription;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;
    private UserRepository userRepository;
    private PaymentMethodRepository paymentMethodRepository;
    private PriceChangeHistoryRepository priceChangeHistoryRepository;

    @Transactional
    public Subscription create(Subscription subscription) {
        userRepository.findById(subscription.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (subscription.getPaymentMethod() != null) {
            paymentMethodRepository.findById(subscription.getPaymentMethod().getId())
                    .orElseThrow(() -> new RuntimeException("Payment method not found"));
        }
        return subscriptionRepository.save(subscription);
    }

    public Subscription updatePrice(Long subscriptionId, BigDecimal newPrice) {
        var sub = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        if (sub.getPrice().compareTo(newPrice) != 0) {
            var hist = PriceChangeHistory.builder()
                    .subscription(sub)
                    .oldPrice(sub.getPrice())
                    .newPrice(newPrice)
                    .changedAt(java.time.LocalDateTime.now())
                    .build();
            priceChangeHistoryRepository.save(hist);
            sub.setPrice(newPrice);
        }
        return subscriptionRepository.save(sub);
    }

    public BigDecimal monthlySpend(Long userId) {
        return subscriptionRepository.findUserIdAndActiveTrue(userId).stream()
                .map(s -> s.getBillingCycle() == BillingCycle.MONTHLY
                        ? s.getPrice()
                        : s.getPrice().divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal annualSpend(Long userId) {
        return subscriptionRepository.findUserIdAndActiveTrue(userId).stream()
                .map(s -> s.getBillingCycle() == BillingCycle.ANNUAL
                        ? s.getPrice()
                        : s.getPrice().multiply(BigDecimal.valueOf(12)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);


    }
}