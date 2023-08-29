DROP DATABASE hotel;
CREATE DATABASE hotel;

use hotel;


CREATE TABLE booking (

	id INT AUTO_INCREMENT,
	entry_date TIMESTAMP NOT NULL,
	departure_date TIMESTAMP NOT NULL,
	price DOUBLE,
	payment_method VARCHAR(20) NOT NULL,
	PRIMARY KEY(id)
)Engine=InnoDB;

CREATE TABLE guest (

	id INT AUTO_INCREMENT,
	name VARCHAR(50)NOT NULL,
	lastname VARCHAR(100)NOT NULL,
	birthdate TIMESTAMP,
	nationality VARCHAR(50),
	telephone VARCHAR(15), CONSTRAINT chk_telephone CHECK (telephone REGEXP '^[0-9]{7,10}$'),
	id_booking INT,
	FOREIGN KEY(id_booking)REFERENCES booking(id),
	PRIMARY KEY(id)
)Engine=InnoDB;

CREATE TABLE user_data (

	id INT AUTO_INCREMENT,
	login VARCHAR(50) NOT NULL,
	password VARCHAR(50)NOT NULL,
	PRIMARY KEY(id)
)Engine=InnoDB;

INSERT INTO user_data(login,password) 
VALUES('abeltran','1234'),
	  ('bbeltran','5678'),
	  ('cbeltran','9876'),
	  ('dbeltran','5432'),
	  ('ebeltran','1234'),
	  ('fbeltran','5678'),
	  ('gbeltran','9876'),
	  ('hbeltran','5432'),
	  ('ibeltran','1234'),
	  ('jbeltran','5678');


