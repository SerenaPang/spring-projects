CREATE DATABASE MUSICSTORE;

CREATE TABLE USER (
  user_id INTEGER NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(255),
  PRIMARY KEY (user_id)
);

CREATE TABLE THEATER (
	theater_id INTEGER NOT NULL,
	zipcode VARCHAR(255),
	city VARCHAR(255),
	PRIMARY KEY(theater_id)
);

CREATE TABLE MOVIE(
	movie_id INTEGER NOT NULL AUTO_INCREMENT,
	movie_name VARCHAR(255),
	theater_id INTEGER,
	availability INTEGER,
	showtime_id INTEGER,
	price DECIMAL(6,2),
	PRIMARY KEY(movie_id),
	FOREIGN KEY(theater_id) REFERENCES THEATER(theater_id),
	FOREIGN KEY(showtime_id) REFERENCES SHOWTIME(showtime_id)
);

CREATE TABLE SHOWTIME(
	showtime_id INTEGER NOT NULL AUTO_INCREMENT,
	movie_id INTEGER,
	theater_id INTEGER,
	availability INTEGER,
	showtime DATETIME,
	PRIMARY KEY(showtime_id),
	FOREIGN KEY(movie_id) REFERENCES MOVIE(movie_id),
	FOREIGN KEY(theater_id) REFERENCES THEATER(theater_id)
);

CREATE TABLE SHOWTIME_THEATER(){
	showtime_id INTEGER,
	theater_id INTEGER,
	movie_id INTEGER,
	PRIMARY KEY (showtime_id, theater_id,movie_id),
	FOREIGN KEY(showtime_id) REFERENCES SHOWTIME(showtime_id),
	FOREIGN KEY(theater_id) REFERENCES THEATER(theater_id),
	FOREIGN KEY(movie_id) REFERENCES MOVIE(movie_id)
}

CREATE TABLE TICKET(
	ticket_id INTEGER NOT NULL AUTO_INCREMENT,
	user_id INTEGER,
	movie_id INTEGER,
	movie_name VARCHAR(255),
	theater_id INTEGER,
	availability INTEGER,
	showtime_id INTEGER,
	seat VARCHAR(255),
	price DECIMAL(6,2),
	PRIMARY KEY(ticket_id),
	FOREIGN KEY(movie_id) REFERENCES MOVIE(movie_id),
	FOREIGN KEY(showtime_id) REFERENCES SHOWTIME(showtime_id),
	FOREIGN KEY(theater_id) REFERENCES THEATER(theater_id)
);

CREATE TABLE FOOD (
	food_id INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	price DECIMAL(6,2)
);

CREATE TABLE DRINK (
	drink_id INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
	price DECIMAL(6,2)
);

CREATE TABLE ORDERS (
	 order_id INTEGER NOT NULL,
	 user_id INTEGER NOT NULL,
	 total_price DECIMAL(6,2),
	 sale_date DATETIME,
	 status VARCHAR(100),
	 PRIMARY KEY(order_id, user_id),
	 FOREIGN KEY(user_id) REFERENCES USER(user_id)
);

CREATE TABLE ORDER_ITEM (
	order_item_id Integer NOT NULL,
	order_id integer NOT NULL,
	ticket_id Integer,
	food_id Integer,
	drink_id Integer,
	quantity Integer,
	price DECIMAL(6,2),
	PRIMARY KEY (order_item_id, order_id),
	FOREIGN KEY (order_id) REFERENCES ORDERS(order_id),
	FOREIGN KEY (ticket_id) REFERENCES TICKET(ticket_id),
	FOREIGN KEY (food_id) REFERENCES FOOD(food_id),
	FOREIGN KEY (drink_id) REFERENCES DRINK(drink_id),
);

-- USER
INSERT INTO USER VALUES(1, 'Abby');

--ORDERS
--DATETIME 'YYYY-MM-DD hh:mm:ss'
INSERT INTO ORDERS VALUES(1, 4, 12, 0, '2024-08-27 09:15:07', 'ORDER PLACED');


-- ORDER_ITEM
INSERT INTO ORDER_ITEM VALUES(1, 1, 1, 1, 20);

-- Drop tables
DROP TABLE USER;
DROP TABLE FOOD;
DROP TABLE THEATER;
DROP TABLE TICKET;
DROP TABLE DRINK;
DROP TABLE ORDER_ITEM;
DROP TABLE ORDERS;



