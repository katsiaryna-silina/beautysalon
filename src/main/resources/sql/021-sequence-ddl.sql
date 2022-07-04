--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:add-sequence-table splitStatements:true
CREATE TABLE SALON.SEQUENCE
(
    ID         INT UNSIGNED NOT NULL PRIMARY KEY,
    TABLE_NAME VARCHAR(64) NOT NULL
);
--rollback DROP TABLE IF EXISTS SALON.SEQUENCE
