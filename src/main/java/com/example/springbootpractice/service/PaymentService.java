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
import com.example.springbootpractice.model.entity.UserPoint;
import com.example.springbootpractice.model.enums.PaymentType;
import com.example.springbootpractice.repository.CardRepository;
import com.example.springbootpractice.repository.UserPaymentHistoryRepository;
import com.example.springbootpractice.repository.UserPointRepository;
import com.example.springbootpractice.util.BigDecimalUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
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
  public PaymentApprovalResponse createPointApprovalInfo(PaymentApprovalRequest request) {
    var user = userService.getUser(request.userId());
    var merchant = merchantService.getMerchant(request.merchantId());
    UserPoint userPoint = userPointRepository.findByUserSeqAndCurrency(user.getSeq(),
            request.currency())
        .orElseThrow(() -> new ApiErrorException(ErrorCode.NOT_FOUND_USER_POINT));

    var fees = request.getFees();
    var amountTotal = request.amount().add(fees);
    if (BigDecimalUtils.isGreaterThan(amountTotal, userPoint.getBalance())) {
      throw new ApiErrorException(ErrorCode.INSUFFICIENT_POINT);
    }
    userPoint.deductBalance(amountTotal);
    var paymentId = userPaymentHistoryRepository.save(
            UserPaymentHistory.toPointHistory(
                user.getSeq(),
                merchant.getSeq(),
                amountTotal,
                request.currency()
            ))
        .getSeq();
    return PaymentApprovalResponse.of(paymentId, amountTotal, request.currency());

  }

  @Transactional
  public PaymentApprovalResponse createCardApprovalInfo(PaymentApprovalRequest request) {
    var paymentDetails = request.paymentDetails();
    var user = userService.getUser(request.userId());
    var merchant = merchantService.getMerchant(request.merchantId());
    var card = cardRepository.findByUserSeqAndCardNum(user.getSeq(), paymentDetails.cardNumber())
        .orElseThrow(() -> new ApiErrorException(ErrorCode.NOT_FOUND));
    checkCardDetailInfo(card, paymentDetails);

    if (!Boolean.TRUE.equals(card.getIsUsed())) {
      throw new ApiErrorException(ErrorCode.UNUSED_CARD);
    }

    var fees = request.getFees();
    var amountTotal = request.amount().add(fees);

    // 신용카드 결제의 경우 바로 결제
    if (PaymentType.CREDIT_CARD.equals(request.paymentMethod())) {
      var paymentId = userPaymentHistoryRepository.save(
              UserPaymentHistory.toCardHistory(
                  user.getSeq(),
                  card.getSeq(),
                  merchant.getSeq(),
                  amountTotal,
                  request.currency()))
          .getSeq();
      return PaymentApprovalResponse.of(paymentId, amountTotal, request.currency());
    }

    // 직불카드 결제의 경우 잔액 확인 후 결제 처리
    if (!request.currency().equals(card.getCurrency())) {
      throw new ApiErrorException(ErrorCode.DIFFERENT_CURRENCY);
    }
    if (BigDecimalUtils.isGreaterThan(amountTotal, card.getBalance())) {
      throw new ApiErrorException(ErrorCode.INSUFFICIENT_CARD_BALANCE);
    }
    card.deductBalance(amountTotal);
    var paymentId = userPaymentHistoryRepository.save(
            UserPaymentHistory.toCardHistory(
                user.getSeq(),
                card.getSeq(),
                merchant.getSeq(),
                amountTotal,
                request.currency()))
        .getSeq();
    return PaymentApprovalResponse.of(paymentId, amountTotal, request.currency());
  }

  private void checkCardDetailInfo(Card card, PaymentDetails paymentDetails) {
    // cvv 코드가 일치하지 않는 경우 에러 처리
    if (!paymentDetails.cvv().equals(card.getCvv())) {
      throw new ApiErrorException(ErrorCode.INCORRECT_CARD_DETAIL_INFO);
    }

    // 만료 일시가 일치하지 않는 경우 에러 처리
    if (card.isNotSameExpiryDate(
        paymentDetails.getExpiryMonth(),
        paymentDetails.getExpiryYear())
    ) {
      throw new ApiErrorException(ErrorCode.INCORRECT_CARD_DETAIL_INFO);
    }
  }

}
