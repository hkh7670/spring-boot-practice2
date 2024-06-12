package com.example.springbootpractice.model.dto;

import com.example.springbootpractice.model.enums.CurrencyType;
import com.example.springbootpractice.model.enums.PaymentStatusType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record PaymentApprovalResponse(
    long paymentId,
    PaymentStatusType status,
    BigDecimal amountTotal,
    CurrencyType currency,
    LocalDateTime timestamp

) {

  public static PaymentApprovalResponse of(long paymentId, BigDecimal amountTotal, CurrencyType currency) {
    return PaymentApprovalResponse.builder()
        .paymentId(paymentId)
        .status(PaymentStatusType.APPROVED)
        .amountTotal(amountTotal)
        .currency(currency)
        .timestamp(LocalDateTime.now())
        .build();
  }


}
