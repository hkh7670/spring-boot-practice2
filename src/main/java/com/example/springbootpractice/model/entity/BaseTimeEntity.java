package com.example.springbootpractice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public abstract class BaseTimeEntity {

  @CreatedDate
  @Column(name = "REG_DATE", updatable = false, nullable = false)
  private LocalDateTime regDate;

  @LastModifiedDate
  @Column(name = "UPT_DATE", nullable = false)
  private LocalDateTime uptDate;

}
