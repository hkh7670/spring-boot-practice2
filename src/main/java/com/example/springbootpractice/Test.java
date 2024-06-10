package com.example.springbootpractice;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {

  public static void main(String[] args) {
    BigDecimal a = new BigDecimal("12345.1234567");
    System.out.println(a);
    a = a.setScale(0, RoundingMode.FLOOR);
    System.out.println(a);

  }

}
