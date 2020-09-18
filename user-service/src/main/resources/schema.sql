DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS USER_PROFILE;
DROP TABLE IF EXISTS PURCHASE;

CREATE TABLE USER
(
    ID       INT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(50)  NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    ENABLED  INT(1)       NOT NULL,
    LOCKED   INT(1)       NOT NULL,
    EXPIRED  INT(1)       NOT NULL
);


CREATE TABLE USER_PROFILE
(
    ID            INT AUTO_INCREMENT PRIMARY KEY,
    FIRST_NAME    VARCHAR(250) NOT NULL,
    LAST_NAME     VARCHAR(250) NOT NULL,
    BIRTH_DATE    DATE         NOT NULL,
    MOBILE_NUMBER VARCHAR(20)  NOT NULL,
    EMAIL         VARCHAR(50)  NOT NULL,
    ADDRESS       VARCHAR(250) NOT NULL,
    ZIP_CODE      VARCHAR(20)  NOT NULL
);


CREATE TABLE PURCHASE
(
    ID            INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID       INT  NOT NULL,
    PRODUCT_ID    INT  NOT NULL,
    CREATION_DATE DATE NOT NULL
);

ALTER TABLE USER_PROFILE ADD FOREIGN KEY (ID) REFERENCES USER(ID);

ALTER TABLE PURCHASE ADD FOREIGN KEY (USER_ID) REFERENCES USER(ID)