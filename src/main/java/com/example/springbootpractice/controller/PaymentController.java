package com.example.springbootpractice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
