INSERT INTO user_info (USER_ID, USER_NAME, IS_USED, REG_DATE, UPT_DATE)
VALUES ('ghhan', '한규호', true, now(), now());

INSERT INTO user_point (USER_SEQ, CURRENCY, BALANCE, REG_DATE, UPT_DATE)
VALUES (1, 'USD', 100.15, now(), now());

INSERT INTO user_point (USER_SEQ, CURRENCY, BALANCE, REG_DATE, UPT_DATE)
VALUES (1, 'KRW', 100000, now(), now());

INSERT INTO card_info (CARD_NUM, CARD_TYPE, USER_SEQ, CURRENCY, balance, CVV, EXPIRY_YEAR,
                       EXPIRY_MONTH, IS_USED, REG_DATE, UPT_DATE)
VALUES ('1234567890', 'DEBIT', 1, 'USD', 100, '131', 2027, 12, true, now(), now());

INSERT INTO card_info (CARD_NUM, CARD_TYPE, USER_SEQ, CURRENCY, balance, CVV, EXPIRY_YEAR,
                       EXPIRY_MONTH, IS_USED, REG_DATE, UPT_DATE)
VALUES ('9876543210', 'CREDIT', 1, 'USD', 0, '131', 2027, 12, true, now(), now());

INSERT INTO merchant_info (MERCHANT_ID, MERCHANT_NAME, IS_USED, REG_DATE, UPT_DATE)
VALUES ('merchant123', '김밥천국', true, now(), now());