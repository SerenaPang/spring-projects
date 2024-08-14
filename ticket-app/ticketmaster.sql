CREATE DATABASE TICKETMASTER;

CREATE TABLE USER (
  id_user INTEGER NOT NULL AUTO_INCREMENT,
  name_user VARCHAR(255),
  PRIMARY KEY (id_user)
);

CREATE TABLE EVENT (
  id_event INTEGER NOT NULL AUTO_INCREMENT,
  name_event VARCHAR(255),
  type_event VARCHAR(255),
  date_event Date,
  PRIMARY KEY (id_event)
);

CREATE TABLE RESERVATION (
  id_reservation INTEGER NOT NULL AUTO_INCREMENT,
  id_user INTEGER,
  id_event INTEGER,
  status VARCHAR(255), 
  PRIMARY KEY (id_reservation),
  FOREIGN KEY (id_user) REFERENCES USER(id_user),
  FOREIGN KEY (id_event) REFERENCES EVENT(id_event)
);

-- USER
INSERT INTO USER VALUES(1, 'MiuMiu');
INSERT INTO USER VALUES(2, 'Coco');
INSERT INTO USER VALUES(3, 'Gigi');

-- EVENT
INSERT INTO EVENT VALUES(1, 'The Unbearable Lightness Of Being', 'theater', '2024-09-22');
INSERT INTO EVENT VALUES(2, 'The Milky Way', 'concert', '2024-08-22');
INSERT INTO EVENT VALUES(3, 'Olympic', 'sport', '2024-7-22');

-- RESERVATION
INSERT INTO RESERVATION VALUES(1,1,1);
INSERT INTO RESERVATION VALUES(2,2,2);
INSERT INTO RESERVATION VALUES(3,3,3);

-- Drop tables
DROP TABLE USER;
DROP TABLE EVENT;
DROP TABLE RESERVATION;

/*
mysql> describe user;
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| id_user   | int          | NO   | PRI | NULL    | auto_increment |
| name_user | varchar(255) | YES  |     | NULL    |                |
+-----------+--------------+------+-----+---------+----------------+

mysql> describe event;
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id_event   | int          | NO   | PRI | NULL    | auto_increment |
| name_event | varchar(255) | YES  |     | NULL    |                |
| type_event | varchar(255) | YES  |     | NULL    |                |
| date_event | date         | YES  |     | NULL    |                |
+------------+--------------+------+-----+---------+----------------+

mysql> describe reservation;
+----------------+------+------+-----+---------+----------------+
| Field          | Type | Null | Key | Default | Extra          |
+----------------+------+------+-----+---------+----------------+
| id_reservation | int  | NO   | PRI | NULL    | auto_increment |
| id_user        | int  | YES  | MUL | NULL    |                |
| id_event       | int  | YES  | MUL | NULL    |                |
+----------------+------+------+-----+---------+----------------+
*/








