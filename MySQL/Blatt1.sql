# AUFGABE 5-4
CREATE TABLE users (
	firstname varchar(255),
    lastname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email 	VARCHAR(255),
    birthdate date,
    PRIMARY KEY(email) 
) ENGINE = INNODB;
    
CREATE TABLE addresses ( 
	id int(32) PRIMARY KEY auto_increment NOT NULL,
    street varchar(255),
    number varchar(255),
    zipcode int(32),
    city varchar(255),
    user VARCHAR(255),
    FOREIGN KEY (user) REFERENCES users(email)
) ENGINE = InnoDB;

CREATE TABLE invoices (
	invoiceno int(32) PRIMARY KEY auto_increment,
    date date,
    status ENUM('posted', 'packing', 'shipped'),
    user VARCHAR(255),
    FOREIGN KEY (user) REFERENCES users(email)
) ENGINE = INNODB;
    
CREATE TABLE orders (
	invoiceno int(32),
    productid int(32),
    price double,
    quantity int(32),
    FOREIGN KEY (productid) REFERENCES products(id),
    FOREIGN KEY(invoiceno) REFERENCES invoices(invoiceno)
) ENGINE = INNODB;
    
CREATE TABLE products ( 
	id int(32) PRIMARY KEY auto_increment ,
    name varchar(255),
    price double,
    descripion text,
    manufacturer varchar(255),
    FOREIGN KEY (manufacturer) REFERENCES manufacturers(name)
) ENGINE = INNODB; 
    
CREATE TABLE manufacturers (
	name varchar(255) PRIMARY KEY,
    infourl text
) ENGINE = INNODB;


CREATE VIEW Bestellung (Bestellnummer, Datum, Status, Email, Abzahl, Betrag, Differenz) as
SELECT i.invoiceno, i.date, i.status, i.user, sum(temp.quantiy), sum(temp.price)
FROM invoices i, products p,
	(SELECT quantity, price
     FROM orders, invoices i2
     WHERE i2.invoiceno = orders.invoiceno) temp
;
CREATE FUNCTION exchange (euro double)
	RETURNS double
 begin
set @dollar = euro*1.10951;
return @dollar;
end $$