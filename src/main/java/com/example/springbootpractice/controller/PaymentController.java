package com.example.springbootpractice.controller;

import com.example.springbootpractice.model.dto.PaymentApprovalRequest;
import com.example.springbootpractice.model.dto.PaymentApprovalResponse;
import com.example.springbootpractice.model.dto.PaymentEstimateRequest;
import com.example.springbootpractice.model.dto.PaymentEstimateResponse;
import com.example.springbootpractice.model.dto.UserBalanceResponse;
import com.example.springbootpractice.service.MerchantService;
import com.example.springbootpractice.service.PaymentService;
import com.example.springbootpractice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

  private final PaymentService paymentService;
  private final UserService userService;
  private final MerchantService merchantService;

  @GetMapping("/balance/{userId}")
  public UserBalanceResponse getBalance(@PathVariable String userId) {
    return paymentService.getBalance(userId);
  }

  @PostMapping("/estimate")
  public PaymentEstimateResponse getPaymentEstimate(
      @RequestBody @Valid PaymentEstimateRequest request) {
    return paymentService.getPaymentEstimate(request);
  }

  @PostMapping("/approval")
  public PaymentApprovalResponse requestPaymentApproval(
      @RequestBody @Valid PaymentApprovalRequest request) {
    request.validate();
    return switch (request.paymentMethod()) {
      case POINT -> paymentService.createPointApprovalInfo(request);
      case DEBIT_CARD, CREDIT_CARD -> paymentService.createCardApprovalInfo(request);
    };
//    var user = userService.getUser(request.userId());
//    var merchant = merchantService.getMerchant(request.merchantId());
//    return switch (request.paymentMethod()) {
//      case POINT -> paymentService.createPointApprovalInfo(user, request);
//      case DEBIT_CARD, CREDIT_CARD -> paymentService.createCardApprovalInfo(user, request);
//    };
  }

}
