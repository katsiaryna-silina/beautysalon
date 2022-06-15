--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:populate-user-statuses-table splitStatements:true
INSERT INTO SALON.USER_STATUSES (STATUS)
VALUES ('active');
INSERT INTO SALON.USER_STATUSES (STATUS)
VALUES ('blocked');
INSERT INTO SALON.USER_STATUSES (STATUS)
VALUES ('deleted');