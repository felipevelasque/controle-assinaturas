package com.felipe.subscriptions.controllers;

import com.felipe.subscriptions.entities.Subscription;
import com.felipe.subscriptions.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @PostMapping
    public ResponseEntity<Subscription> create(@RequestBody Subscription sub) {
        Subscription created = service.create(sub);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/users/{userId}")
    public List<Subscription> findByUser(@PathVariable Long userId) {
        return service.findByUser(userId);
    }

    @PatchMapping("/{id}/price")
    public Subscription updatePrice(@PathVariable Long id, @RequestParam BigDecimal newPrice) {
        return service.updatePrice(id, newPrice);
    }

    @GetMapping("/users/{userId}/spend/monthly")
    public BigDecimal monthlySpend(@PathVariable Long userId) {
        return service.monthlySpend(userId);
    }

    @GetMapping("/users/{userId}/spend/annual")
    public BigDecimal annualSpend(@PathVariable Long userId) {
        return service.annualSpend(userId);
    }

}
