DROP TABLE IF EXISTS Clients_Accounts;
DROP TABLE IF EXISTS Clients;
DROP TABLE IF EXISTS AddressDict;
DROP TABLE IF EXISTS StreetDict;
DROP TABLE IF EXISTS CityDict;
DROP TABLE IF EXISTS CountryDict;
DROP TABLE IF EXISTS Accounts_Cards;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Cards;

CREATE TABLE Cards (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  number CHAR (10) NOT NULL,
  pin CHAR (4) NOT NULL
);

CREATE TABLE Accounts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  number CHAR (16) NOT NULL,
  balance NUMERIC (20, 4) DEFAULT 0,
  currency CHAR (3) NOT NULL DEFAULT 'RUB'
);

CREATE TABLE Accounts_Cards (
  id INT AUTO_INCREMENT PRIMARY KEY,
  account_id INT NOT NULL,
  card_id INT NOT NULL UNIQUE,
  FOREIGN KEY (account_id) REFERENCES Accounts(id),
  FOREIGN KEY (card_id) REFERENCES Cards(id)
);

CREATE TABLE CountryDict (
  id INT AUTO_INCREMENT PRIMARY KEY,
  country VARCHAR (240)
);

CREATE TABLE CityDict (
  id INT AUTO_INCREMENT PRIMARY KEY,
  country_id INT NOT NULL,
  city VARCHAR (240),
  FOREIGN KEY (country_id) REFERENCES CountryDict(id)
);

CREATE TABLE StreetDict (
  id INT AUTO_INCREMENT PRIMARY KEY,
  city_id INT NOT NULL,
  street VARCHAR (240),
  FOREIGN KEY (city_id) REFERENCES CityDict(id)
);

CREATE TABLE AddressDict (
  id INT AUTO_INCREMENT PRIMARY KEY,
  street_id INT NOT NULL,
  house INT NOT NULL,
  flat INT NOT NULL,
  FOREIGN KEY (street_id) REFERENCES StreetDict(id)
);

CREATE TABLE Clients (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR (240) NOT NULL,
  code VARCHAR (20) NOT NULL,
  age INT NOT NULL,
  address_id INT NOT NULL,
  FOREIGN KEY (address_id) REFERENCES AddressDict(id)
);

CREATE TABLE Clients_Accounts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  client_id INT NOT NULL,
  account_id INT NOT NULL UNIQUE,
  FOREIGN KEY (client_id) REFERENCES Clients(id),
  FOREIGN KEY (account_id) REFERENCES Accounts(id)
);

INSERT INTO Cards (number, pin) VALUES
  ('1111122222', '1111'),
  ('2222233333', '2222'),
  ('3333344444', '3333'),
  ('4444455555', '4444');

INSERT INTO Accounts (number, balance) VALUES
  ('1111222233334444', 300),
  ('5555666677778888', 150),
  ('3333444455556666', 200);

INSERT INTO Accounts_Cards (card_id, account_id) VALUES
  (1, 2),
  (2, 3),
  (3, 2),
  (4, 1);

INSERT INTO CountryDict (country) VALUES
  ('Россия');

INSERT INTO CityDict (country_id, city) VALUES
  (1, 'Москва'),
  (1, 'Ростов');

INSERT INTO StreetDict (city_id, street) VALUES
  (1, 'Кутузовский проспект'),
  (2, 'улица Садовая'),
  (1, 'улица Тверская');

INSERT INTO AddressDict (street_id, house, flat) VALUES
  (1, 23, 570),
  (2, 12, 340),
  (1, 2, 43),
  (3, 90, 88);

INSERT INTO Clients (name, code, age, address_id) VALUES
  ('Иванов Иван Иванович', '111', 34, 1),
  ('Петров Петр Петрович', '222', 25, 4);

INSERT INTO Clients_Accounts (client_id, account_id) VALUES
  (1, 2),
  (2, 1),
  (2, 3);