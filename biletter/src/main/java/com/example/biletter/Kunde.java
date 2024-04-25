package com.example.biletter;


// POJO for Kunde Class
public class Kunde {
    String fornavn, etternavn, film, telefon, epost;
    int antall, id;

    public Kunde(String fornavn, String etternavn, String film, String telefon, String epost, int antall, int id) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.film = film;
        this.telefon = telefon;
        this.epost = epost;
        this.antall = antall;
        this.id = id;
    }

    public Kunde() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
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
