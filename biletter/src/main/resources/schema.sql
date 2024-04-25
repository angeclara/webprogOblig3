CREATE TABLE Filmer
(
    film varchar(100) NOT NULL,
    PRIMARY KEY (film)
);

CREATE TABLE Kunde
(
    id INTEGER AUTO_INCREMENT NOT NULL,
    fornavn varchar(30) NOT NULL,
    etternavn varchar(30) NOT NULL,
    film varchar(155) NOT NULL,
    telefon varchar(8) NOT NULL,
    epost varchar(55) NOT NULL,
    antall INTEGER NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (film) REFERENCES Filmer
);