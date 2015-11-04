DROP KEYSPACE IF EXISTS scooltivity;

CREATE KEYSPACE IF NOT EXISTS scooltivity WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1};

CREATE TABLE IF NOT EXISTS scooltivity.accounts ( 
	account_id uuid,
	email text,
	first_name text,
	last_name text,
	school_name text,
	type text,
	birthday timestamp,
	password text,
	token text,
	PRIMARY KEY (email) 
);