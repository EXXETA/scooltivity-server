DROP KEYSPACE IF EXISTS scooltivity;

CREATE KEYSPACE IF NOT EXISTS scooltivity WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1};

CREATE TABLE IF NOT EXISTS scooltivity.school (
	school_id uuid,
	school_name text,
	PRIMARY KEY (school_name)
);

CREATE TABLE IF NOT EXISTS scooltivity.account (
	account_id uuid,
	school_id uuid,
	email text,
	first_name text,
	last_name text,
	school_name text,
	type text,
	birthday timestamp,
	password text,
	password_salt text,
	auth_token text,
	registered_by uuid,
	PRIMARY KEY (email, school_id) 
);

CREATE TABLE IF NOT EXISTS scooltivity.activity (
	activity_id uuid,
	school_id uuid,
	title text,
	description text,
	PRIMARY KEY (school_id, title)
);

CREATE INDEX IF NOT EXISTS ON scooltivity.activity(activity_id);

CREATE TABLE IF NOT EXISTS scooltivity.activity_by_account (
	activity_id uuid,
	account_id uuid,
	PRIMARY KEY (account_id, activity_id)
);

-- CREATE INDEX IF NOT EXISTS ON scooltivity.activity_by_account(activity_id);