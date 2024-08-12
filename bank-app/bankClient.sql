CREATE DATABASE TUCYBANK;

CREATE TABLE CLIENT(
	id_cilent INTEGER NOT NULL,
	name VARCHAR(255),
	last_name VARCHAR(255),
	id_account Integer,
	PRIMARY KEY (id_cilent)
);

/*
INSERT INTO CLIENT VALUES(1, 'Duck','Ding', 1);
INSERT INTO CLIENT VALUES(2, 'Su','Qing', 2);
*/

CREATE TABLE ACCOUNT(
	id_account Integer NOT NULL,
	id_cilent INTEGER,
	total_balance FLOAT,
	PRIMARY KEY (id_account),
	FOREIGN KEY (id_cilent) REFERENCES CLIENT(id_cilent)
);

/*
INSERT INTO ACCOUNT VALUES(1, 1,1000);
INSERT INTO ACCOUNT VALUES(2, 2,3000);
*/

CREATE TABLE ACTIVITY(
	id_activity Integer NOT NULL AUTO_INCREMENT,
	id_account Integer,
	date_activity Date,
	type_activity VARCHAR(255),
	amount FLOAT,
	PRIMARY KEY (id_activity),
	FOREIGN KEY (id_account) REFERENCES ACCOUNT(id_account)
);

/*
INSERT INTO ACTIVITY VALUES(1, 1, '2020-03-24', 'deposit');
*/

-- Drop tables
DROP TABLE CLIENT;
DROP TABLE ACCOUNT;
DROP TABLE ACTIVITY;

/*
mysql> describe account;
+------------+-------+------+-----+---------+-------+
| Field      | Type  | Null | Key | Default | Extra |
+------------+-------+------+-----+---------+-------+
| id_account | int   | NO   | PRI | NULL    |       |
| id_cilent  | int   | YES  | MUL | NULL    |       |
| amount     | float | YES  |     | NULL    |       |
+------------+-------+------+-----+---------+-------+
3 rows in set (0.01 sec)

mysql> describe activity;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| id_activity   | int          | NO   | PRI | NULL    | auto_increment |
| id_account    | int          | YES  | MUL | NULL    |                |
| date_activity | date         | YES  |     | NULL    |                |
| type_activity | varchar(255) | YES  |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+
4 rows in set (0.00 sec)

mysql> describe client;
+------------+--------------+------+-----+---------+-------+
| Field      | Type         | Null | Key | Default | Extra |
+------------+--------------+------+-----+---------+-------+
| id_cilent  | int          | NO   | PRI | NULL    |       |
| name       | varchar(255) | YES  |     | NULL    |       |
| last_name  | varchar(255) | YES  |     | NULL    |       |
| id_account | int          | YES  |     | NULL    |       |
+------------+--------------+------+-----+---------+-------+
*/