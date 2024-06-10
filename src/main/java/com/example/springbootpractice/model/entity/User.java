package com.example.springbootpractice.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
