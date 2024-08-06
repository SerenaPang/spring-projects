CREATE DATABASE TUCYBANK;

CREATE TABLE CLIENT(
	id_cilent INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	last_name VARCHAR(255),
	id_account Integer,
	PRIMARY KEY (cilent_id),
);

CREATE TABLE ACCOUNT(
	id_account Integer NOT NULL AUTO_INCREMENT,
	id_cilent INTEGER,
	amount FLOAT,
	PRIMARY KEY (id_account),
	FOREIGN KEY (id_cilent) REFERENCES CLIENT(id_cilent)
);

CREATE TABLE ACTIVITY(
	id_activity Integer NOT NULL AUTO_INCREMENT,,
	type_activity VARCHAR(255),,
	id_account Integer,
	Date date,
	PRIMARY KEY (id_activity),
	FOREIGN KEY (id_account) REFERENCES ACCOUNT(id_account)
);

-- Drop tables
DROP TABLE CLIENT;
DROP TABLE ACCOUNT;
DROP TABLE ACTIVITY;