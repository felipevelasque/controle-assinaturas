package com.felipe.subscriptions.repositories;

import com.felipe.subscriptions.entities.PriceChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceChangeHistoryRepository extends JpaRepository<PriceChangeHistory, Long> {
    List<PriceChangeHistory> findBySubscriptionId(Long subscriptionId);
}
