CREATE DATABASE authorbook;

CREATE TABLE AUTHOR (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE BOOK (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  isbn VARCHAR(255),
  id_author INTEGER,
  PRIMARY KEY (id),
  FOREIGN KEY (id_author) REFERENCES AUTHOR(id)
);

-- AUTHORS
INSERT INTO AUTHOR VALUES(1, 'Milan Kundera');

-- BOOKS
INSERT INTO BOOK VALUES(1, 'The Unbearable Lightness Of Being', "tgy-h567gh-j6h", 1);


-- Drop tables
DROP TABLE AUTHOR_BOOK;
DROP TABLE AUTHOR;
DROP TABLE BOOK;

/*
mysql> describe AUTHOR;
+-------+--------------+------+-----+---------+----------------+
| Field | Type         | Null | Key | Default | Extra          |
+-------+--------------+------+-----+---------+----------------+
| id    | int          | NO   | PRI | NULL    | auto_increment |
| name  | varchar(255) | YES  |     | NULL    |                |
+-------+--------------+------+-----+---------+----------------+

mysql> describe BOOK;
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| id        | int          | NO   | PRI | NULL    | auto_increment |
| name      | varchar(255) | YES  |     | NULL    |                |
| isbn      | varchar(255) | YES  |     | NULL    |                |
| id_author | int          | YES  | MUL | NULL    |                |
+-----------+--------------+------+-----+---------+----------------+
*/








