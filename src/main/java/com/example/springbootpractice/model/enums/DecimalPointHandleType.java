package com.example.springbootpractice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.RoundingMode;

@Getter
@RequiredArgsConstructor
public enum DecimalPointHandleType {
  // 화폐 별 소숫점 핸들링 타입
  USD(2, RoundingMode.FLOOR),
  KRW(0, RoundingMode.FLOOR),
  ;

  private final int scale;
  private final RoundingMode roundingMode;
}
