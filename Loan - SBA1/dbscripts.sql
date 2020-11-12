DROP DATABASE IF EXISTS loanDB;

CREATE DATABASE loanDB;

USE loanDB;

CREATE TABLE USERS(
USER_ID INT,
USER_NAME VARCHAR(50),
DOB DATE,
GENDER VARCHAR(10),
EMAIL_ID VARCHAR(50),
PWD VARCHAR(30),
CREATED_DT DATE
);

CREATE TABLE loanDB_roles(
 	user_name varchar(20) primary key,
 	password varchar(20) not null,
 	role varchar(10) not null
 ); 

CREATE TABLE LOAN_REQ(
LOAN_APPL_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
USER_NAME VARCHAR(50),
LOAN_NAME VARCHAR(100),
LOAN_AMOUNT DECIMAL(10,2),
LOAN_APPL_DATE DATE,
LOAN_BUS_STRUCTURE VARCHAR(30),
LOAN_BILL_IND VARCHAR(30),
MOBILE INT,
ADDRESS VARCHAR(200),
PURPOSE VARCHAR(200),
LOAN_TERM_MNTHS INT,
STATUS VARCHAR(20));