INSERT INTO USER_PROFILE (FIRST_NAME,LAST_NAME,BIRTH_DATE ,MOBILE_NUMBER,EMAIL,ADDRESS,ZIP_CODE) VALUES ('Jacob','Miller',TO_DATE( '2-JAN-1991', 'DD-MON-YYYY' ),'+989854574880','Miller@gmail.com','Iran, Esfahan keshvari 8445','5854256201');
INSERT INTO USER_PROFILE (FIRST_NAME,LAST_NAME,BIRTH_DATE ,MOBILE_NUMBER,EMAIL,ADDRESS,ZIP_CODE) VALUES ('John','Trueman',TO_DATE( '7-FEB-1988', 'DD-MON-YYYY' ),'+999832074870','Truman@gmail.com','UAE, Dubai team view 20','4456985210');

INSERT INTO USER (USERNAME,PASSWORD,ENABLED,LOCKED,EXPIRED) VALUES ('test','{bcrypt}$2a$10$eWJYngKQ5SwTOVZFlcWCrOQ3SpcALXgaqoLJZlptDqZtR7jil47Ne',1,0,0);
INSERT INTO USER (USERNAME,PASSWORD,ENABLED,LOCKED,EXPIRED) VALUES ('j_truman','{bcrypt}$2a$10$eWJYngKQ5SwTOVZFlcWCrOQ3SpcALXgaqoLJZlptDqZtR7jil47Ne',1,0,0);

INSERT INTO PURCHASE (USER_ID,PRODUCT_ID,CREATION_DATE) VALUES (1,1,TO_DATE( '2-JAN-2019', 'DD-MON-YYYY' ));
INSERT INTO PURCHASE (USER_ID,PRODUCT_ID,CREATION_DATE) VALUES (1,2,TO_DATE( '2-FEB-2018', 'DD-MON-YYYY' ));
INSERT INTO PURCHASE (USER_ID,PRODUCT_ID,CREATION_DATE) VALUES (2,1,TO_DATE( '1-OCT-2020', 'DD-MON-YYYY' ));


