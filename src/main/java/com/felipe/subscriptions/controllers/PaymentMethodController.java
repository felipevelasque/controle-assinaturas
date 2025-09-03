package com.felipe.subscriptions.controllers;

import com.felipe.subscriptions.entities.PaymentMethod;
import com.felipe.subscriptions.repositories.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodRepository repo;

    @PostMapping
    public ResponseEntity<PaymentMethod> create(@RequestBody PaymentMethod pm) {
        PaymentMethod saved = repo.save(pm);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/user/{userId}")
    public List<PaymentMethod> listByUser(@PathVariable Long userId) {
        return repo.findUserById(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> findById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}