CREATE DATABASE cat_house;

CREATE TABLE PET(
	id_pet INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	age INTEGER,
	breed VARCHAR(255),
	description  VARCHAR(255),
	PRIMARY KEY(id_pet)
);


-- PET
INSERT INTO PET VALUES(1,'Silver', 5, 'Calico', 'Adopted');
INSERT INTO PET VALUES(2,'Diana', 5, 'Calico', 'Adopted');
INSERT INTO PET VALUES(3,'Blue', 1, 'RagDoll', 'Available');
INSERT INTO PET VALUES(4,'Huahua', 1, 'Maiecoon', 'Available');
/**INSERT INTO PET VALUES(0, 'Lily', 1, 1, 'Available');/

SELECT * FROM PET;


-- Drop tables
DROP TABLE PET;


/**
pet

+--------+--------+------+----------+-------------+
| id_pet | name   | age  | breed    | description |
+--------+--------+------+----------+-------------+
|      1 | Silver |    5 | Calico   | Adopted     |
|      2 | Diana  |    5 | Calico   | Adopted     |
|      3 | Blue   |    1 | RagDoll  | Available   |
|      4 | Huahua |    1 | Maiecoon | Available   |
+--------+--------+------+----------+-------------+
*/











