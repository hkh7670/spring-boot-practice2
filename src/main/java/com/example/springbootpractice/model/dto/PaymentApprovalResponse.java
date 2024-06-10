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


}
