package com.example.springbootpractice.model.entity;

import com.example.springbootpractice.model.enums.CardType;
import com.example.springbootpractice.model.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Table(name = "CARD_INFO",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "CARD_NUM_UNIQUE",
                        columnNames = ("CARD_NUM")
                )
        })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Card extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "CARD_NUM")
    private String cardNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "CARD_TYPE")
    private CardType cardType;

    @Column(name = "USER_ID")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Comment("기본 결제 화폐 단위")
    private CurrencyType currency;

    private BigDecimal balance;

    @Column(name = "IS_USED")
    private Boolean isUsed;

}
