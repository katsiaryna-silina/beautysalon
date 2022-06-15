--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:add-orders-table splitStatements:true
CREATE TABLE SALON.ORDERS
(
    ID                  INT AUTO_INCREMENT PRIMARY KEY,
    ORDER_DATE_TIME     DATETIME NOT NULL DEFAULT NOW(),
    VISIT_DATE_TIME     DATETIME,
    USER_ID             INT      NOT NULL,
    ORDER_STATUS_ID     INT      NOT NULL,
    PRICE_WITH_DISCOUNT DOUBLE   NOT NULL,
    FEEDBACK_ID         INT      NOT NULL,
    CONSTRAINT FK_ORDERS_ORDER_FEEDBACKS FOREIGN KEY (FEEDBACK_ID) REFERENCES SALON.ORDER_FEEDBACKS (ID),
    CONSTRAINT FK_ORDERS_USERS FOREIGN KEY (USER_ID) REFERENCES SALON.USERS (ID),
    CONSTRAINT FK_ORDERS_ORDER_STATUSES FOREIGN KEY (ORDER_STATUS_ID) REFERENCES SALON.ORDER_STATUSES (ID)
);

CREATE INDEX IDX_ORDERS_FEEDBACK_ID ON SALON.ORDERS (FEEDBACK_ID);
CREATE INDEX IDX_ORDERS_USER_ID ON SALON.ORDERS (USER_ID);
CREATE INDEX IDX_ORDERS_ORDER_STATUS_ID ON SALON.ORDERS (ORDER_STATUS_ID);
--rollback DROP TABLE IF EXISTS SALON.ORDERS
