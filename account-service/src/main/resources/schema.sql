DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS BILL;
DROP TABLE IF EXISTS PAYMENT_TYPE;

CREATE TABLE ACCOUNT
(
    ID            INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID       INT    NOT NULL,
    CREATION_DATE DATE   NOT NULL,
    LOCKED        INT(1) NOT NULL
);
CREATE TABLE BILL
(
    ID              INT AUTO_INCREMENT PRIMARY KEY,
    ACCOUNT_ID      INT        NOT NULL,
    PURCHASE_ID     INT        NOT NULL,
    TOTAL_AMOUNT    NUMBER(10) NOT NULL,
    TOTAL_COUNT     INT(10)    NOT NULL,
    PAYMENT_TYPE_ID INT        NOT NULL
);
CREATE TABLE PAYMENT_TYPE
(
    ID   INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR NOT NULL,
    CODE VARCHAR NOT NULL
);

ALTER TABLE BILL
    ADD FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNT (ID);

ALTER TABLE BILL
    ADD FOREIGN KEY (PAYMENT_TYPE_ID) references PAYMENT_TYPE (ID);

