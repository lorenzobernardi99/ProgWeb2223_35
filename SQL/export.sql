-- ============================

-- Questo file è stato creato con la utility dblook di Derby.
-- Indicatore data/ora: 2023-06-02 14:05:55.047
-- Database di origine: Tum4WorldDB
-- URL di connessione: jdbc:derby://localhost:1527/Tum4WorldDB;user=APP;
-- appendLogs: false

-- ----------------------------------------------
-- Istruzioni DDL per tabelle
-- ----------------------------------------------

CREATE TABLE "APP"."ROLES" ("ID" INTEGER NOT NULL, "ROLE" VARCHAR(16));

CREATE TABLE "APP"."USERS" ("ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "NAME" VARCHAR(16) NOT NULL, "SURNAME" VARCHAR(32), "BIRTH_DATE" DATE, "EMAIL_ADDRESS" VARCHAR(128) NOT NULL, "TELEPHONE_NUMBER" VARCHAR(32), "ROLE" INTEGER, "USERNAME" VARCHAR(64), "PASSWORD" VARCHAR(32));

CREATE TABLE "APP"."DONATIONS" ("ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "CONTRIBUTOR" INTEGER, "AMOUNT" FLOAT, "DATE" DATE);

-- ----------------------------------------------
-- Istruzioni DDL per chiavi
-- ----------------------------------------------

-- PRIMARY/UNIQUE
ALTER TABLE "APP"."USERS" ADD CONSTRAINT "USERS_id" PRIMARY KEY ("ID");

ALTER TABLE "APP"."ROLES" ADD CONSTRAINT "ROLES_id" PRIMARY KEY ("ID");

-- FOREIGN
ALTER TABLE "APP"."USERS" ADD CONSTRAINT "USERS_role_fk" FOREIGN KEY ("ROLE") REFERENCES "APP"."ROLES" ("ID") ON DELETE NO ACTION ON UPDATE NO ACTION;

