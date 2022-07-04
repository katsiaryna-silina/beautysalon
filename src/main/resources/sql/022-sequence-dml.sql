--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:populate-sequence-table splitStatements:true
INSERT INTO SALON.SEQUENCE (ID, TABLE_NAME)
VALUES (0, 'ORDERS');
