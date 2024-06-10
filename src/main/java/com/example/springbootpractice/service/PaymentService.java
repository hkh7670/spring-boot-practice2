package com.example.springbootpractice.service;

import com.example.springbootpractice.exception.ApiErrorException;
import com.example.springbootpractice.exception.ErrorCode;
import com.example.springbootpractice.model.dto.PaymentApprovalRequest;
import com.example.springbootpractice.model.dto.PaymentApprovalRequest.PaymentDetails;
import com.example.springbootpractice.model.dto.PaymentApprovalResponse;
import com.example.springbootpractice.model.dto.PaymentEstimateRequest;
import com.example.springbootpractice.model.dto.PaymentEstimateResponse;
import com.example.springbootpractice.model.dto.UserBalanceResponse;
import com.example.springbootpractice.model.entity.Card;
import com.example.springbootpractice.model.entity.UserPaymentHistory;
import com.example.springbootpractice.model.enums.PaymentType;
import com.example.springbootpractice.repository.CardRepository;
import com.example.springbootpractice.repository.UserPaymentHistoryRepository;
import com.example.springbootpractice.repository.UserPointRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final UserService userService;
  private final MerchantService merchantService;
  private final UserPointRepository userPointRepository;
  private final CardRepository cardRepository;
  private final UserPaymentHistoryRepository userPaymentHistoryRepository;


  @Transactional(readOnly = true)
  public UserBalanceResponse getBalance(String userId) {
    var user = userService.getUser(userId);
    var userPointInfos = userPointRepository.findByUserSeq(user.getSeq());
    var cardInfos = cardRepository.findByUserSeq(user.getSeq());
    return UserBalanceResponse.of(userId, userPointInfos, cardInfos);
  }

  @Transactional(readOnly = true)
  public PaymentEstimateResponse getPaymentEstimate(PaymentEstimateRequest request) {
    var user = userService.getUser(request.userId());
    var merchant = merchantService.getMerchant(request.merchantId());
    return PaymentEstimateResponse.of(request.amount(), request.currency());
  }

  @Transactional
  public PaymentApprovalResponse createPaymentApprovalInfo(PaymentApprovalRequest request) {
    return switch (request.paymentMethod()) {
      case POINT -> createPointApprovalInfo(request);
      case DEBIT_CARD, CREDIT_CARD -> createCardApprovalInfo(request);
    };
  }

  private PaymentApprovalResponse createPointApprovalInfo(PaymentApprovalRequest request) {
    var user = userService.getUser(request.userId());
    var merchant = merchantService.getMerchant(request.merchantId());
    var userPoint = userPointRepository.findByUserSeq(user.getSeq());
    return null;
  }

  private PaymentApprovalResponse createCardApprovalInfo(PaymentApprovalRequest request) {
    var paymentDetails = request.paymentDetails();
    var user = userService.getUser(request.userId());
    var merchant = merchantService.getMerchant(request.merchantId());
    var card = cardRepository.findByUserSeqAndCardNum(user.getSeq(), paymentDetails.cardNumber())
        .orElseThrow(() -> new ApiErrorException(ErrorCode.NOT_FOUND));
    checkCardDetailInfo(card, paymentDetails);
    var fees = request.currency().convert(request.amount().multiply(BigDecimal.valueOf(0.03)));
    var amountTotal = request.amount().add(fees);

    // 신용카드 결제의 경우 바로 결제
    if (PaymentType.CREDIT_CARD.equals(request.paymentMethod())) {
      var paymentId = userPaymentHistoryRepository.save(UserPaymentHistory.builder()
          .userSeq(user.getSeq())
          .cardSeq(card.getSeq())
          .merchantSeq(merchant.getSeq())
          .usedCardAmount(request.amount())
          .usedPointAmount(BigDecimal.ZERO)
          .currency(request.currency())
          .build()).getSeq();
    }
    // 직불카드 결제의 경우 잔액을 확인

    return null;
  }

  private void checkCardDetailInfo(Card card, PaymentDetails paymentDetails) {
    // cvv 코드가 일치하지 않는 경우 에러 처리
    if (!paymentDetails.cvv().equals(card.getCvv())) {
      throw new ApiErrorException(ErrorCode.BAD_REQUEST);
    }

    // 만료 일시가 일치하지 않는 경우 에러 처리
    if (card.isNotSameExpiryDate(
        paymentDetails.getExpiryMonth(),
        paymentDetails.getExpiryYear())
    ) {
      throw new ApiErrorException(ErrorCode.BAD_REQUEST);
    }
  }

}
