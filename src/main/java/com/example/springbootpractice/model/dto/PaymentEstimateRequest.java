package com.example.springbootpractice.model.dto;

import com.example.springbootpractice.model.enums.CurrencyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PaymentEstimateRequest(
    @NotNull
    BigDecimal amount,
    @NotNull
    CurrencyType currency,
    @NotNull
    String merchantId,
    @NotBlank
    String userId
) {


}
