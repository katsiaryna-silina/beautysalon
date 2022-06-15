--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:update-users-table-password-length splitStatements:true
ALTER TABLE SALON.USERS
    MODIFY PASSWORD VARCHAR (72) NOT NULL;
--rollback ALTER TABLE SALON.USERS MODIFY PASSWORD VARCHAR(30) NOT NULL;
