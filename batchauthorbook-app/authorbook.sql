CREATE DATABASE authors;

CREATE TABLE AUTHOR (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

-- AUTHORS
INSERT INTO AUTHOR VALUES(1, 'Milan Kundera');


-- Drop tables
DROP TABLE AUTHOR;

/*
mysql> describe AUTHOR;
+-------+--------------+------+-----+---------+----------------+
| Field | Type         | Null | Key | Default | Extra          |
+-------+--------------+------+-----+---------+----------------+
| id    | int          | NO   | PRI | NULL    | auto_increment |
| name  | varchar(255) | YES  |     | NULL    |                |
+-------+--------------+------+-----+---------+----------------+

*/








