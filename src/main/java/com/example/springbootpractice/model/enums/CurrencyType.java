package com.example.springbootpractice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrencyType {
  USD(DecimalPointHandleType.USD),
  KRW(DecimalPointHandleType.KRW),
  ;


  private final DecimalPointHandleType handleType;
}
