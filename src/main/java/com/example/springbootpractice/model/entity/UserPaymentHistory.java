package com.example.springbootpractice.model.entity;

import com.example.springbootpractice.model.enums.CurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "USER_PAYMENT_HISTORY")
@Comment("유저 결제 내역")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPaymentHistory extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  @Column(name = "USER_SEQ")
  private Long userSeq;

  @Column(name = "CARD_SEQ")
  private Long cardSeq;

  @Column(name = "MERCHANT_SEQ")
  private Long merchantSeq;

  @Column(name = "USED_CARD_AMOUNT")
  private BigDecimal usedCardAmount;

  @Column(name = "USED_POINT_AMOUNT")
  private BigDecimal usedPointAmount;

  @Enumerated(EnumType.STRING)
  private CurrencyType currency;

  public static UserPaymentHistory toPointHistory(long userSeq, long merchantSeq, BigDecimal amountTotal,
      CurrencyType currency) {
    return UserPaymentHistory.builder()
        .userSeq(userSeq)
        .cardSeq(null)
        .merchantSeq(merchantSeq)
        .usedCardAmount(BigDecimal.ZERO)
        .usedPointAmount(amountTotal)
        .currency(currency)
        .build();
  }

  public static UserPaymentHistory toCardHistory(long userSeq, long cardSeq, long merchantSeq,
      BigDecimal amountTotal,
      CurrencyType currency) {
    return UserPaymentHistory.builder()
        .userSeq(userSeq)
        .cardSeq(cardSeq)
        .merchantSeq(merchantSeq)
        .usedCardAmount(amountTotal)
        .usedPointAmount(BigDecimal.ZERO)
        .currency(currency)
        .build();
  }
}
