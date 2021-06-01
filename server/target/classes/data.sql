DROP TABLE IF EXISTS Clients_Accounts;
DROP TABLE IF EXISTS Clients;
DROP TABLE IF EXISTS Address_Dict;
DROP TABLE IF EXISTS Street_Dict;
DROP TABLE IF EXISTS City_Dict;
DROP TABLE IF EXISTS Country_Dict;
DROP TABLE IF EXISTS Accounts_Cards;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Cards;

CREATE TABLE Cards (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  number CHAR (10) NOT NULL,
  pin CHAR (4) NOT NULL,
  expiration_date DATE NOT NULL
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
  cards_id INT NOT NULL UNIQUE,
  FOREIGN KEY (account_id) REFERENCES Accounts(id),
  FOREIGN KEY (cards_id) REFERENCES Cards(id)
);

CREATE TABLE Country_Dict (
  id INT AUTO_INCREMENT PRIMARY KEY,
  country VARCHAR (240)
);

CREATE TABLE City_Dict (
  id INT AUTO_INCREMENT PRIMARY KEY,
  country_id INT NOT NULL,
  city VARCHAR (240),
  FOREIGN KEY (country_id) REFERENCES Country_Dict(id)
);

CREATE TABLE Street_Dict (
  id INT AUTO_INCREMENT PRIMARY KEY,
  city_id INT NOT NULL,
  street VARCHAR (240),
  FOREIGN KEY (city_id) REFERENCES City_Dict(id)
);

CREATE TABLE Address_Dict (
  id INT AUTO_INCREMENT PRIMARY KEY,
  street_id INT NOT NULL,
  house INT NOT NULL,
  flat INT NOT NULL,
  FOREIGN KEY (street_id) REFERENCES Street_Dict(id)
);

CREATE TABLE Clients (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR (240) NOT NULL,
  code VARCHAR (20) NOT NULL,
  age INT NOT NULL,
  address_id INT NOT NULL,
  FOREIGN KEY (address_id) REFERENCES Address_Dict(id)
);

CREATE TABLE Clients_Accounts (
  id INT AUTO_INCREMENT PRIMARY KEY,
  client_id INT NOT NULL,
  accounts_id INT NOT NULL UNIQUE,
  FOREIGN KEY (client_id) REFERENCES Clients(id),
  FOREIGN KEY (accounts_id) REFERENCES Accounts(id)
);

INSERT INTO Cards (number, pin, expiration_date) VALUES
  ('1111122222', '1111', '2022-12-20'),
  ('2222233333', '2222', '2021-05-24'),
  ('3333344444', '3333', '2018-01-20'),
  ('4444455555', '4444', '2023-09-17');

INSERT INTO Accounts (number, balance) VALUES
  ('1111222233334444', 300),
  ('5555666677778888', 150),
  ('3333444455556666', 200);

INSERT INTO Accounts_Cards (cards_id, account_id) VALUES
  (1, 2),
  (2, 3),
  (3, 2),
  (4, 1);

INSERT INTO Country_Dict (country) VALUES
  ('Россия');

INSERT INTO City_Dict (country_id, city) VALUES
  (1, 'Москва'),
  (1, 'Ростов');

INSERT INTO Street_Dict (city_id, street) VALUES
  (1, 'Кутузовский проспект'),
  (2, 'улица Садовая'),
  (1, 'улица Тверская');

INSERT INTO Address_Dict (street_id, house, flat) VALUES
  (1, 23, 570),
  (2, 12, 340),
  (1, 2, 43),
  (3, 90, 88);

INSERT INTO Clients (name, code, age, address_id) VALUES
  ('Иванов Иван Иванович', '111', 34, 1),
  ('Петров Петр Петрович', '222', 25, 4);

INSERT INTO Clients_Accounts (client_id, accounts_id) VALUES
  (1, 2),
  (2, 1),
  (2, 3);