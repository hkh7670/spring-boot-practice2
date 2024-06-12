package com.example.springbootpractice.model.dto;

import com.example.springbootpractice.exception.ApiErrorException;
import com.example.springbootpractice.exception.ErrorCode;
import com.example.springbootpractice.model.enums.CurrencyType;
import com.example.springbootpractice.model.enums.PaymentType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PaymentApprovalRequest(
    @NotBlank
    String userId,

    @NotNull
    BigDecimal amount,

    @NotNull
    CurrencyType currency,

    @NotNull
    String merchantId,

    @NotNull
    PaymentType paymentMethod,

    @Valid
    PaymentDetails paymentDetails

) {

  public record PaymentDetails(
      @NotBlank
      String cardNumber,
      @NotBlank
      String expiryDate,
      @NotBlank
      String cvv
  ) {

    public int getExpiryMonth() {
      try {
        return Integer.parseInt(this.expiryDate.split("/")[0]);
      } catch (Exception e) {
        throw new ApiErrorException(ErrorCode.BAD_REQUEST);
      }
    }

    public int getExpiryYear() {
      try {
        return Integer.parseInt("20" + this.expiryDate.split("/")[1]);
      } catch (Exception e) {
        throw new ApiErrorException(ErrorCode.BAD_REQUEST);
      }
    }

  }

  public void validate() {
    // 카드 결제 요청인 경우 결제 상세정보 필수
    if (PaymentType.CARD_PAYMENT_TYPE.contains(this.paymentMethod)
        && this.paymentMethod == null) {
      throw new ApiErrorException(ErrorCode.BAD_REQUEST);
    }

  }

  /**
   * 결제 수수료를 리턴한다. 원금의 3% 화폐 단위를 고려하여 리턴
   *
   * @return 결제 수수료
   */
  public BigDecimal getFees() {
    return this.currency.convert(
        this.amount.multiply(BigDecimal.valueOf(0.03))
    );
  }
}
