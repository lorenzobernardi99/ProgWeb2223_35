-- ============================

-- Questo file è stato creato con la utility dblook di Derby.
-- Indicatore data/ora: 2023-06-10 23:49:28.72
-- Database di origine: Tum4WorldDB
-- URL di connessione: jdbc:derby://localhost:1527/Tum4WorldDB;user=APP;
-- appendLogs: false

-- ----------------------------------------------
-- Istruzioni DDL per tabelle
-- ----------------------------------------------

CREATE TABLE "APP"."USERS" ("ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "NAME" VARCHAR(16) NOT NULL, "SURNAME" VARCHAR(32), "BIRTH_DATE" DATE, "EMAIL_ADDRESS" VARCHAR(128) NOT NULL, "TELEPHONE_NUMBER" VARCHAR(32), "ROLE" INTEGER, "USERNAME" VARCHAR(64), "PASSWORD" VARCHAR(32));

CREATE TABLE "APP"."DONATIONS" ("ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "CONTRIBUTOR" INTEGER, "AMOUNT" DOUBLE, "DATE" DATE);

-- ----------------------------------------------
-- Istruzioni DDL per chiavi
-- ----------------------------------------------

-- PRIMARY/UNIQUE
ALTER TABLE "APP"."USERS" ADD CONSTRAINT "USERS_username" UNIQUE ("USERNAME");

ALTER TABLE "APP"."USERS" ADD CONSTRAINT "USERS_id" PRIMARY KEY ("ID");

ALTER TABLE "APP"."DONATIONS" ADD CONSTRAINT "DONATIONS_pk" PRIMARY KEY ("ID");

-- FOREIGN
ALTER TABLE "APP"."DONATIONS" ADD CONSTRAINT "DONATIONS_USERS_ID_fk" FOREIGN KEY ("CONTRIBUTOR") REFERENCES "APP"."USERS" ("ID") ON DELETE NO ACTION ON UPDATE NO ACTION;

