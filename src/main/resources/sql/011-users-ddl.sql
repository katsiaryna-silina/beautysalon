--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:update-users-table-add-phone-number splitStatements:true
ALTER TABLE SALON.USERS
    ADD PHONE_NUMBER VARCHAR(20);
--rollback ALTER TABLE SALON.USERS DROP PHONE_NUMBER;
