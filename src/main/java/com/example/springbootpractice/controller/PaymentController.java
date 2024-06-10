package com.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    @GetMapping("/balance/{userId}")
    public ResponseEntity<?> getBalance(@PathVariable Long userId) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/estimate")
    public ResponseEntity<?> getPaymentEstimate() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/approval")
    public ResponseEntity<?> requestPaymentApproval(@PathVariable Long userId) {
        return ResponseEntity.ok(null);
    }

}
