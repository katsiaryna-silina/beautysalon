--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:populate-order-statuses-table splitStatements:true
INSERT INTO SALON.ORDER_STATUSES (STATUS, DESCRIPTION)
VALUES ('waiting for confirmation', 'Order is waiting for confirmation.');
INSERT INTO SALON.ORDER_STATUSES (STATUS, DESCRIPTION, ACCESSIBLE_TO_ROLE_ID)
VALUES ('confirmed', 'Order is confirmed.', 1);
INSERT INTO SALON.ORDER_STATUSES (STATUS, DESCRIPTION)
VALUES ('completed', 'Order is completed.');
INSERT INTO SALON.ORDER_STATUSES (STATUS, DESCRIPTION, ACCESSIBLE_TO_ROLE_ID)
VALUES ('declined by admin', 'Order is declined by admin.', 1);
INSERT INTO SALON.ORDER_STATUSES (STATUS, DESCRIPTION, ACCESSIBLE_TO_ROLE_ID)
VALUES ('declined by client', 'Order is declined by client.', 2);
