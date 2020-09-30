INSERT INTO WAREHOUSE_TYPE (NAME, CODE)
VALUES ('car warehouse', 'CRW')
     , ('guns and ammous', 'GWR');

INSERT INTO WAREHOUSE (NAME, WAREHOUSE_NUMBER, CAPACITY)
VALUES ('warehouse for cars', '22ab33', 100)
     , ('warehouse for guns', '212bb3', 1000);


INSERT INTO WAREHOUSE_PRODUCT (PRODUCT_ID, WAREHOUSE_ID, COUNT)
VALUES (1, 1, 100),
       (2, 2, 200);
