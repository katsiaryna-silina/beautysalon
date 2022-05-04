--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:add-services-table splitStatements:true
CREATE TABLE SALON.SERVICES
(
    ID          INT AUTO_INCREMENT PRIMARY KEY,
    NAME        VARCHAR(50) NOT NULL,
    DESCRIPTION TEXT        NOT NULL,
    PRICE       DECIMAL(10, 2)
);

CREATE UNIQUE INDEX IDX_UN_SERVICES_NAME ON SALON.SERVICES (NAME);
--rollback DROP TABLE IF EXISTS SALON.SERVICES
