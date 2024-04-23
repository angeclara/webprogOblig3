package com.example.biletter;

public class Kunde {
    String navn, film, telefon, epost;
    int antall;

    public Kunde(int antall, String film, String navn, String telefon, String epost) {
        this.antall = antall;
        this.film = film;
        this.navn = navn;
        this.telefon = telefon;
        this.epost = epost;
    }

    public Kunde() {}

    public int getAntall() {
        return antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }
}
