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
	PRIMARY KEY (showtime_id, theater_id, movie_id),
	FOREIGN KEY(showtime_id) REFERENCES SHOWTIME(showtime_id),
	FOREIGN KEY(theater_id) REFERENCES THEATER(theater_id),
	FOREIGN KEY(movie_id) REFERENCES MOVIE(movie_id)
}

CREATE TABLE TICKET(
	ticket_id INTEGER NOT NULL AUTO_INCREMENT,
	user_id INTEGER,
	movie_id INTEGER,
	theater_id INTEGER,
	showtime_id INTEGER,
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
INSERT INTO USER VALUES(2, 'Boby');
INSERT INTO USER VALUES(3, 'Cathy');

-- THEATER
INSERT INTO THEATER VALUES(1 , "94111", "Moutain View");
INSERT INTO THEATER VALUES(2 , "94222", "Santa Clara");
INSERT INTO THEATER VALUES(3 , "94333", "Redwood City");
INSERT INTO THEATER VALUES(4 , "94555", "Sunnyvale");

-- MOVIE
INSERT INTO MOVIE VALUES(1, "The Lord of the Ring", 1, 100, 1, 3.5);
INSERT INTO MOVIE VALUES(2, "Wicked", 2, 100, 2, 4);
INSERT INTO MOVIE VALUES(3, "Harry Potter", 3, 100, 3, 5);
INSERT INTO MOVIE VALUES(4, "Game of Throne", 4, 100, 4, 6.6);
	
-- SHOWTIME
INSERT INTO SHOWTIME VALUES(1, 1, 1, 100, '2024-11-27 09:15:00');
INSERT INTO SHOWTIME VALUES(2, 1, 1, 100, '2024-11-27 09:45:00');
INSERT INTO SHOWTIME VALUES(3, 1, 1, 100, '2024-11-27 09:55:00');

INSERT INTO SHOWTIME VALUES(4, 2, 2, 100, '2024-11-27 09:15:00');
INSERT INTO SHOWTIME VALUES(5, 2, 2, 100, '2024-11-27 10:15:00');
INSERT INTO SHOWTIME VALUES(6, 2, 2, 100, '2024-11-27 11:15:00');

INSERT INTO SHOWTIME VALUES(7, 3, 3, 100, '2024-11-27 09:15:00');
INSERT INTO SHOWTIME VALUES(8, 3, 3, 100, '2024-11-27 09:25:00');
INSERT INTO SHOWTIME VALUES(9, 3, 3, 100, '2024-11-27 09:45:00');

INSERT INTO SHOWTIME VALUES(10, 4, 4, 100, '2024-11-27 09:15:00');
INSERT INTO SHOWTIME VALUES(11, 4, 4, 100, '2024-11-27 09:55:00');
	
-- TICKET
INSERT INTO TICKET VALUES(1, 1, 1, 1, 1, 3.5);
INSERT INTO TICKET VALUES(2, 1, 1, 1, 1, 3.5);
INSERT INTO TICKET VALUES(3, 2, 1, 1, 1, 3.5);
INSERT INTO TICKET VALUES(4, 3, 1, 1, 1, 3.5);
INSERT INTO TICKET VALUES(5, 3, 1, 1, 1, 3.5);

-- FOOD
INSERT INTO FOOD VALUES(1, "Popcorn");
INSERT INTO FOOD VALUES(2, "Chicken tender");
INSERT INTO FOOD VALUES(3, "Pizza");

-- DRINK
INSERT INTO DRINK VALUES(1, "Coke");
INSERT INTO DRINK VALUES(2, "Juice");
INSERT INTO DRINK VALUES(3, "Water");


--ORDERS
--DATETIME 'YYYY-MM-DD hh:mm:ss'
INSERT INTO ORDERS VALUES(1, 4, 12.5, '2024-08-27 09:15:07', 'ORDER PLACED');


-- ORDER_ITEM
INSERT INTO ORDER_ITEM VALUES(1, 1, 1, 1, 1, 1, 20.5);

-- Drop tables
DROP TABLE USER;
DROP TABLE FOOD;
DROP TABLE THEATER;
DROP TABLE TICKET;
DROP TABLE DRINK;
DROP TABLE ORDER_ITEM;
DROP TABLE ORDERS;



