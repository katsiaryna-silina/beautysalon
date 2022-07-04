--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:populate-order-statuses-table splitStatements:true
INSERT INTO SALON.ORDER_STATUSES (STATUS, DESCRIPTION)
VALUES ('waiting for confirmation', 'Order is waiting for confirmation.');
INSERT INTO SALON.ORDER_STATUSES (STATUS, DESCRIPTION)
VALUES ('confirmed', 'Order is confirmed.');
INSERT INTO SALON.ORDER_STATUSES (STATUS, DESCRIPTION)
VALUES ('completed', 'Order is completed.');
INSERT INTO SALON.ORDER_STATUSES (STATUS, DESCRIPTION)
VALUES ('declined by admin', 'Order is declined by admin.');
INSERT INTO SALON.ORDER_STATUSES (STATUS, DESCRIPTION)
VALUES ('declined by client', 'Order is declined by client.');
