--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:populate-users-table splitStatements:true
INSERT INTO SALON.USERS (USERNAME, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME, DISCOUNT_STATUS_ID, ROLE_ID, PHONE_NUMBER)
VALUES ('admin', '$2a$12$ClJ4jihN1cEXRuPZlke4t.9SPWvzFikIi4TxqLoU9yXmvZHGJwV9O', 'admin@gmail.com',
        'Alex', 'Stone', 5, 1, '+375293214');