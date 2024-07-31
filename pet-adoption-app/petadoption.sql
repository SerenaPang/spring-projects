CREATE TABLE PET(
	id_pet INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	id_type INTEGER,
	age INTEGER,
	status VARCHAR(255),
	PRIMARY KEY(id_pet),
	FOREIGN KEY (id_type) REFERENCES PET_TYPE(id_type)
);

CREATE TABLE USER(
	id_user INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	last_name VARCHAR(255),
	PRIMARY KEY(id_user)
);

CREATE TABLE USER_PET(
	id_user INTEGER,
	id_pet INTEGER,
	adoption_date DATE,
	PRIMARY KEY (id_user, id_pet),
    FOREIGN KEY (id_pet) REFERENCES PET(id_pet),
    FOREIGN KEY (id_user) REFERENCES USER(id_user)
);

CREATE TABLE PET_TYPE(
	id_type INTEGER NOT NULL,
	type VARCHAR(255),
	PRIMARY KEY(id_type)
);

-- PET
INSERT INTO PET VALUES(0, 'Silver', 2, 4, 'Adopted');
INSERT INTO PET VALUES(0, 'Diana', 2, 4, 'Adopted');
INSERT INTO PET VALUES(0, 'Snooby', 1, 8, 'Available');
INSERT INTO PET VALUES(0, 'Huahua', 1, 2, 'Available');
INSERT INTO PET VALUES(0, 'Lily', 1, 1, 'Available');

SELECT * FROM PET;

-- USER
INSERT INTO USER VALUES(0, 'Abby', 'Chen');
INSERT INTO USER VALUES(0, 'Bobo', 'Xi');
INSERT INTO USER VALUES(0, 'Coco', 'Yan');

-- USER_PET
INSERT INTO USER_PET VALUES(1, 1, '2020-07-01');
INSERT INTO USER_PET VALUES(2, 1, '2020-07-01');


-- PET_TYPE
INSERT INTO PET_TYPE VALUES(1, 'DOG');
INSERT INTO PET_TYPE VALUES(2, 'CAT');

-- Drop tables
DROP TABLE PET;
DROP TABLE USER;
DROP TABLE USER_TYPE;
DROP TABLE PET_TYPE;

/**
pet
+---------+--------------+------+-----+---------+----------------+
| Field   | Type         | Null | Key | Default | Extra          |
+---------+--------------+------+-----+---------+----------------+
| id_pet  | int          | NO   | PRI | NULL    | auto_increment |
| name    | varchar(255) | YES  |     | NULL    |                |
| id_type | int          | YES  | MUL | NULL    |                |
| age     | int          | YES  |     | NULL    |                |
| status  | varchar(255) | YES  |     | NULL    |                |
+---------+--------------+------+-----+---------+----------------+
5 rows in set (0.03 sec)

pet_type
+---------+--------------+------+-----+---------+-------+
| Field   | Type         | Null | Key | Default | Extra |
+---------+--------------+------+-----+---------+-------+
| id_type | int          | NO   | PRI | NULL    |       |
| type    | varchar(255) | YES  |     | NULL    |       |
+---------+--------------+------+-----+---------+-------+
2 rows in set (0.00 sec)

user
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| id_user   | int          | NO   | PRI | NULL    | auto_increment |
| name      | varchar(255) | YES  |     | NULL    |                |
| last_name | varchar(255) | YES  |     | NULL    |                |
+-----------+--------------+------+-----+---------+----------------+


user_pet
+---------------+------+------+-----+---------+-------+
| Field         | Type | Null | Key | Default | Extra |
+---------------+------+------+-----+---------+-------+
| id_user       | int  | NO   | PRI | NULL    |       |
| id_pet        | int  | NO   | PRI | NULL    |       |
| adoption_date | date | YES  |     | NULL    |       |
+---------------+------+------+-----+---------+-------+
*/











