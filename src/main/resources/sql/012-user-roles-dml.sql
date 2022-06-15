--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:populate-user-roles-table splitStatements:true
INSERT INTO SALON.USER_ROLES (ROLE)
VALUES ('admin');
INSERT INTO SALON.USER_ROLES (ROLE)
VALUES ('client');