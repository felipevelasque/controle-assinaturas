package com.felipe.subscriptions.repositories;

import com.felipe.subscriptions.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    List<PaymentMethod> findUserById(Long userId);
}
