CREATE SEQUENCE NUMERY MINVALUE 0 MAXVALUE 9999999999 INCREMENT BY 1 START WITH 0;

CREATE TABLE LOG (id int PRIMARY KEY, wiadomosc varchar(255) ,dataZmiany date);

create or replace TRIGGER poInsercie AFTER INSERT ON USERS
BEGIN
    INSERT INTO LOG(id,wiadomosc,dataZmiany)
    VALUES(NUMERY.nextval,'Dodano Uzytkownika',sysdate);
END;

