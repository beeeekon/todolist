-- SCHEMA: RecordSchema

-- DROP SCHEMA IF EXISTS "RecordSchema" ;

--CREATE SCHEMA IF NOT EXISTS "RecordSchema"
--    AUTHORIZATION postgres;

drop table "RecordSchema"."RecordTable";
drop sequence "RecordSchema"."record_seq";
create table "RecordSchema"."RecordTable"(
	record_id integer primary key,
	record_name character varying NOT NULL,
    record_deadline timestamp,
    record_is_completed boolean
)
CREATE SEQUENCE "RecordSchema"."record_seq" START WITH 1 INCREMENT BY 1;
ALTER SEQUENCE "RecordSchema"."record_seq" OWNED BY "RecordSchema"."RecordTable".record_id;