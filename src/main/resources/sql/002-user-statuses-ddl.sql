--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:add-user-statuses-table splitStatements:true
CREATE TABLE SALON.USER_STATUSES
(
    ID     INT AUTO_INCREMENT PRIMARY KEY,
    STATUS VARCHAR(20) NOT NULL
);

CREATE UNIQUE INDEX IDX_UN_USER_STATUSES_STATUS ON SALON.USER_STATUSES (STATUS);
--rollback DROP TABLE IF EXISTS SALON.USER_STATUSES
