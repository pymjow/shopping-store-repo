INSERT INTO WAREHOUSE_TYPE (NAME, CODE)
VALUES ('car warehouse', 'CRW')
     , ('guns and ammous', 'GWR');

INSERT INTO WAREHOUSE (NAME, WAREHOUSE_NUMBER, CAPACITY, WAREHOUSE_TYPE_ID)
VALUES ('warehouse for cars', '22ab33', 100, 1)
     , ('warehouse for guns', '212bb3', 1000, 2);


INSERT INTO WAREHOUSE_PRODUCT (PRODUCT_ID, WAREHOUSE_ID, COUNT)
VALUES (1, 1, 100),
       (2, 2, 200);
