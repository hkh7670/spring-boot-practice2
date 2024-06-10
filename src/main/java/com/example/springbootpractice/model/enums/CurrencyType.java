package com.example.springbootpractice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrencyType {
  USD(DecimalPointHandlingType.USD),
  KRW(DecimalPointHandlingType.KRW),
  ;


  private final DecimalPointHandlingType handlingType; // 소숫점 핸들링 타입
}
