-- Generated by Oracle SQL Developer Data Modeler 19.4.0.350.1424
--   at:        2020-07-11 13:15:52 CEST
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



CREATE TABLE activities (
    id              INTEGER NOT NULL,
    name            VARCHAR2(30) NOT NULL,
    data            DATE,
    calories_burnt  INTEGER,
    users_id        INTEGER NOT NULL
);

ALTER TABLE activities ADD CONSTRAINT activities_pk PRIMARY KEY ( id );

CREATE TABLE meals (
    id        INTEGER NOT NULL,
    name      VARCHAR2(40),
    data      DATE,
    users_id  INTEGER NOT NULL
);

ALTER TABLE meals ADD CONSTRAINT meals_pk PRIMARY KEY ( id );

CREATE TABLE products (
    id        INTEGER NOT NULL,
    name      VARCHAR2(40),
    calories  INTEGER,
    meals_id  INTEGER NOT NULL
);

ALTER TABLE products ADD CONSTRAINT products_pk PRIMARY KEY ( id );

CREATE TABLE users (
    id             INTEGER NOT NULL,
    username       VARCHAR2(25),
    password       VARCHAR2(255 CHAR),
    role           INTEGER,
    energy_needed  INTEGER
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( id );

ALTER TABLE activities
    ADD CONSTRAINT activities_users_fk FOREIGN KEY ( users_id )
        REFERENCES users ( id );

ALTER TABLE meals
    ADD CONSTRAINT meals_users_fk FOREIGN KEY ( users_id )
        REFERENCES users ( id );

ALTER TABLE products
    ADD CONSTRAINT products_meals_fk FOREIGN KEY ( meals_id )
        REFERENCES meals ( id );



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             4
-- CREATE INDEX                             0
-- ALTER TABLE                              7
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
