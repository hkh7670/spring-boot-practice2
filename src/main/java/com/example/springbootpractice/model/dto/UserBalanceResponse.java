package com.example.springbootpractice.model.dto;

import com.example.springbootpractice.model.entity.Card;
import com.example.springbootpractice.model.entity.UserPoint;
import com.example.springbootpractice.model.enums.BalanceType;
import com.example.springbootpractice.model.enums.CurrencyType;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record UserBalanceResponse(
    String userId,
    List<BalanceInfo> balanceInfos
) {

  @Builder(access = AccessLevel.PRIVATE)
  public record BalanceInfo(
      BalanceType balanceType,
      BigDecimal balance,
      CurrencyType currency
  ) {

  }

  public static UserBalanceResponse of(String userId, List<UserPoint> pointInfos,
      List<Card> cardInfos) {

    Stream<BalanceInfo> pointBalance = pointInfos.stream()
        .map(item -> BalanceInfo.builder()
            .balanceType(BalanceType.POINT)
            .balance(item.getCurrency().convert(item.getBalance()))
            .currency(item.getCurrency())
            .build());

    Stream<BalanceInfo> cardBalance = cardInfos.stream()
        .map(item -> BalanceInfo.builder()
            .balanceType(BalanceType.CARD)
            .balance(item.getCurrency().convert(item.getBalance()))
            .currency(item.getCurrency())
            .build());

    return UserBalanceResponse.builder()
        .userId(userId)
        .balanceInfos(
            Stream.concat(pointBalance, cardBalance)
                .toList()
        )
        .build();
  }

}
