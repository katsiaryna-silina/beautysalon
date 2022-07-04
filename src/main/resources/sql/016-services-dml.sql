--liquibase formatted sql
--changeset katsiaryna.silina@gmail.com:populate-services-table splitStatements:true
INSERT INTO SALON.SERVICES (NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE)
VALUES ('Combing', 'N', 60, 'Making hair tidy with a comb.', 10.20);
INSERT INTO SALON.SERVICES (NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE)
VALUES ('Nail clipping', 'N', 30, 'Cutting nails using a nail trimmer designed for cats.', 7.60);
INSERT INTO SALON.SERVICES (NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE)
VALUES ('Ear cleaning', 'N', 10, 'Cleaning ears.', 2.50);
INSERT INTO SALON.SERVICES (NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE)
VALUES ('Bathing', 'N', 30, 'Bathing with professional cat shampoo and conditioner.', 6.00);
INSERT INTO SALON.SERVICES (NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE)
VALUES ('Drying', 'N', 20, 'Drying with a hair dryer.', 4.20);
INSERT INTO SALON.SERVICES (NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE)
VALUES ('Haircut', 'N', 30, 'Machine haircut.', 10.50);
INSERT INTO SALON.SERVICES (NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE)
VALUES ('Express molting', 'Y', 90, 'Combing with a special tool, bathing, drying and ear cleaning.', 20.00);
INSERT INTO SALON.SERVICES (NAME, IS_COMPLEX, MINUTES_NEEDED, DESCRIPTION, PRICE)
VALUES ('All complex', 'Y', 150, 'Combing, bathing, drying, ear cleaning, nail clipping and haircut.', 40.00);
