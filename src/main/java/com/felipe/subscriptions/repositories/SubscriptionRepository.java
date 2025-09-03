package com.felipe.subscriptions.repositories;

import com.felipe.subscriptions.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserIdAndActiveTrue(Long userId);

    List<Subscription> findByActiveTrueAndNextBillingDateBetween(LocalDate start, LocalDate end);
}
