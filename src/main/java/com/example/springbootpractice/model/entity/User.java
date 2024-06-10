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
@Table(name = "USER_INFO",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "USER_ID_UNIQUE",
            columnNames = ("USER_ID")
        )
    })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long seq;

  @Column(name = "USER_ID")
  private String userId;

  @Column(name = "USER_NAME")
  private String userName;

  @Column(name = "IS_USED")
  private Boolean isUsed;
}
