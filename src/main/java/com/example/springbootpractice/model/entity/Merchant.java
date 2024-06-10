package com.example.springbootpractice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MERCHANT_INFO",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "MERCHANT_ID_UNIQUE",
            columnNames = ("MERCHANT_ID")
        )
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Merchant extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  @Column(name = "MERCHANT_ID", nullable = false)
  private String merchantId;

  @Column(name = "MERCHANT_NAME", nullable = false)
  private String merchantName;

  @Column(name = "IS_USED", nullable = false)
  private Boolean isUsed;

}
