package com.example.biletter;

// POJO for Filmer class
public class Filmer {
    private String film;

    public Filmer(String film) {
        this.film = film;
    }

    public Filmer() {}

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }
}
