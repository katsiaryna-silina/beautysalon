--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:add-orders-visit-times-table splitStatements:true
CREATE TABLE SALON.ORDERS_VISIT_TIMES
(
    ORDER_ID      INT UNSIGNED NOT NULL,
    VISIT_TIME_ID INT UNSIGNED NOT NULL,
    CONSTRAINT FK_ORDERS_VISIT_TIMES_ORDERS FOREIGN KEY (ORDER_ID) REFERENCES SALON.ORDERS (ID),
    CONSTRAINT FK_ORDERS_VISIT_TIMES_VISIT_TIMES FOREIGN KEY (VISIT_TIME_ID) REFERENCES SALON.VISIT_TIMES (ID)
);
CREATE INDEX IDX_ORDERS_SERVICES_ORDERS ON SALON.ORDERS_VISIT_TIMES (ORDER_ID);
CREATE INDEX IDX_ORDERS_SERVICES_VISIT_TIMES ON SALON.ORDERS_VISIT_TIMES (VISIT_TIME_ID);
--rollback DROP TABLE IF EXISTS SALON.ORDERS_VISIT_TIMES
