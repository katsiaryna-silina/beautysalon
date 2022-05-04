--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:add-order-feedbacks-table splitStatements:true
CREATE TABLE SALON.ORDER_FEEDBACKS
(
    ID       INT PRIMARY KEY,
    MARK     TINYINT,
    FEEDBACK TEXT,
    CONSTRAINT MARK_COLUMN_POSITIVE CHECK (MARK BETWEEN 1 AND 5),
    CONSTRAINT FK_ORDER_FEEDBACKS_ORDERS FOREIGN KEY (ID) REFERENCES SALON.ORDERS (ID)
);
--rollback DROP TABLE IF EXISTS SALON.ORDER_FEEDBACKS
