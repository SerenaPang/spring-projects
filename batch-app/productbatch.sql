CREATE DATABASE products_batch;

CREATE TABLE product (
    id              INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(40),
    created_ts      TIMESTAMP,
    price NUMERIC
);


-- Drop tables
DROP TABLE PRODUCT;









