package com.example.springbootpractice.model.enums;

import java.util.Set;

public enum PaymentType {
  POINT,
  CREDIT_CARD,
  DEBIT_CARD,
  ;

  public static final Set<PaymentType> CARD_PAYMENT_TYPE = Set.of(CREDIT_CARD, DEBIT_CARD);
}
