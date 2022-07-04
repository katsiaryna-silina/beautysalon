--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:add-sequence-function-table splitStatements:true endDelimiter:/
CREATE FUNCTION SALON.NEXTVAL(tName VARCHAR (64))
    RETURNS INT UNSIGNED
MODIFIES SQL DATA
DETERMINISTIC
BEGIN
UPDATE SALON.SEQUENCE
SET ID = LAST_INSERT_ID(ID + 1)
WHERE TABLE_NAME = tName;
RETURN LAST_INSERT_ID();
END
/
--rollback DROP FUNCTION IF EXISTS SALON.NEXTVAL
