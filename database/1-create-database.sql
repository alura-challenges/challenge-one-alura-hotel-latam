
use hotel;

DROP TABLE guest;
DROP TABLE booking;



CREATE TABLE booking (

	id INT AUTO_INCREMENT,
	entry_date DATE,
	departure_date TIMESTAMP,
	price DOUBLE,
	method_payment VARCHAR(20),
	PRIMARY KEY(id)
)Engine=InnoDB;

CREATE TABLE guest (

	id INT AUTO_INCREMENT,
	name VARCHAR(50)NOT NULL,
	lastname VARCHAR(100)NOT NULL,
	birthdate DATE,
	nationality VARCHAR(50),
	telephone VARCHAR(15), CONSTRAINT chk_telephone CHECK (telephone REGEXP '^[0-9]{7,10}$'),
	id_booking INT,
	FOREIGN KEY(id_booking)REFERENCES booking(id),
	PRIMARY KEY(id)
)Engine=InnoDB;

