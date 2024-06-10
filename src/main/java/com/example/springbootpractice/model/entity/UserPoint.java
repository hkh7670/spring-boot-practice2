package com.example.springbootpractice.model.entity;

import com.example.springbootpractice.model.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Table(name = "USER_POINT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class UserPoint extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "USER_SEQ")
    private Long userSeq;

    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    @Comment("잔여 포인트")
    private BigDecimal balance;
}
