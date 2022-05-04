--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:add-user-roles-table splitStatements:true
CREATE TABLE SALON.USER_ROLES
(
    ID   INT AUTO_INCREMENT PRIMARY KEY,
    ROLE VARCHAR(20) NOT NULL
);

CREATE UNIQUE INDEX IDX_UN_USER_ROLES_ROLE ON SALON.USER_ROLES (ROLE);
--rollback DROP TABLE IF EXISTS SALON.USER_ROLES
