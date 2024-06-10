package com.example.springbootpractice.model.dto;

import com.example.springbootpractice.model.enums.CurrencyType;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record PaymentEstimateResponse(
    BigDecimal estimatedTotal,
    BigDecimal fees,
    CurrencyType currency
) {

  public static PaymentEstimateResponse of(BigDecimal amount, CurrencyType currency) {
    var fees = currency.convert(amount.multiply(BigDecimal.valueOf(0.03)));
    return PaymentEstimateResponse.builder()
        .estimatedTotal(currency.convert(amount.add(fees)))
        .fees(fees)
        .currency(currency)
        .build();
  }

}
