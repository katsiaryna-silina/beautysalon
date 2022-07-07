--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:add-order-statuses-table splitStatements:true
CREATE TABLE SALON.ORDER_STATUSES
(
    ID                    INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    STATUS                VARCHAR(30) NOT NULL,
    DESCRIPTION           VARCHAR(60) NOT NULL,
    ACCESSIBLE_TO_ROLE_ID INT UNSIGNED,
    CONSTRAINT FK_ORDER_STATUSES_USER_ROLES FOREIGN KEY (ACCESSIBLE_TO_ROLE_ID) REFERENCES SALON.USER_ROLES (ID)
);

CREATE UNIQUE INDEX IDX_UN_ORDER_STATUSES_STATUS ON SALON.ORDER_STATUSES (STATUS);
CREATE INDEX IDX_ORDER_STATUSES_USER_ROLES ON SALON.ORDER_STATUSES (ACCESSIBLE_TO_ROLE_ID);
--rollback DROP TABLE IF EXISTS SALON.ORDER-STATUSES
