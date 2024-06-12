package com.example.springbootpractice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  BAD_REQUEST(HttpStatus.BAD_REQUEST, "허용되지 않은 요청 입니다."),
  INCORRECT_CARD_DETAIL_INFO(HttpStatus.BAD_REQUEST, "올바르지 않은 카드 상세 정보 입니다."),
  NOT_FOUND(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다."),
  NOT_FOUND_USER(HttpStatus.NOT_FOUND, "유저정보를 찾을 수 없습니다."),
  NOT_FOUND_MERCHANT(HttpStatus.NOT_FOUND, "상점정보를 찾을 수 없습니다."),

  UNUSED_CARD(HttpStatus.UNPROCESSABLE_ENTITY, "미사용 카드 입니다."),




  UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지않은 사용자 입니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류 입니다. 관리자에게 문의하세요."),
  EXTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "외부 서버 오류 입니다. 관리자에게 문의하세요."),
  ;


  private final HttpStatus status;
  private final String message;
}
