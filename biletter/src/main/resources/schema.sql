CREATE TABLE Filmer
(
    film varchar(100) NOT NULL,
    PRIMARY KEY (film)

);

CREATE TABLE Kunde
(
    navn varchar(55) NOT NULL,
    film varchar(155) NOT NULL,
    telefon varchar(8) NOT NULL,
    epost varchar(55) NOT NULL,
    PRIMARY KEY (telefon),
    FOREIGN KEY (film) REFERENCES Filmer
);