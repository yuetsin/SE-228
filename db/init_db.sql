CREATE DATABASE bookie; 

USE bookie; 

CREATE TABLE book_library 
  ( 
     title        VARCHAR(40), 
     author       VARCHAR(10), 
     type         VARCHAR(10), 
     description  VARCHAR(200), 
     storage      INTEGER, 
     cover_url    VARCHAR(70), 
     isbn         VARCHAR(15),
     price        DECIMAL(8, 2), 
     coupon_price DECIMAL(8, 2)
     PRIMARY KEY (isbn) 
     FOREIGN KEY (type) REFERENCES book_type(type_name) ON DELETE SET NULL 
  ); 

CREATE TABLE book_type 
  ( 
     type_name VARCHAR(10) 
     PRIMARY KEY (type_name) 
  ); 