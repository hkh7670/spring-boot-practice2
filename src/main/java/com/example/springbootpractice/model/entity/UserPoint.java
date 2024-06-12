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
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "USER_POINT",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "USER_SEQ_CURRENCY_UNIQUE",
            columnNames = {"USER_SEQ", "CURRENCY"}
        )
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class UserPoint extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  @Column(name = "USER_SEQ", nullable = false)
  private Long userSeq;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CurrencyType currency;

  @Comment("잔여 포인트")
  @Column(nullable = false)
  private BigDecimal balance;

  public void deductBalance(BigDecimal amount) {
    this.balance = balance.subtract(amount);
  }
}
