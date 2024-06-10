INSERT INTO user_info (USER_ID, USER_NAME, IS_USED, REG_DATE, UPT_DATE)
VALUES ('ghhan', '한규호', true, now(), now());

INSERT INTO user_point (USER_SEQ, currency, balance, REG_DATE, UPT_DATE)
VALUES (1, 'USD', 100.15, now(), now());

INSERT INTO user_point (USER_SEQ, currency, balance, REG_DATE, UPT_DATE)
VALUES (1, 'KRW', 100000, now(), now());