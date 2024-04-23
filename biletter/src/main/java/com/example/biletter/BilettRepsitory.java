package com.example.biletter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class BilettRepsitory {

    @Autowired
    public JdbcTemplate db;

    public void saveCustomer(Kunde kunde) {
        String sql = "INSERT INTO Kunde(navn, telefon, epost, film, antall)";
        sql += "VALUES (?, ?, ?, ?, ?)";
        db.update(sql, kunde.getNavn(), kunde.getTelefon(), kunde.getEpost(), kunde.getFilm(),
                kunde.getAntall());
    }

    public List<Kunde> fetchCustomers() {
        String sql = "SELECT * FROM Kunde";
        List<Kunde> allCustomers = db.query(sql, new BeanPropertyRowMapper<>(Kunde.class));
        Collections.sort(allCustomers, new Comparator<Kunde>() {
            @Override
            public int compare(Kunde k1, Kunde k2) {
                return k1.getNavn().compareTo(k2.getNavn());
            }
        });
        return allCustomers;
    }

    public List<Filmer> fetchMovies() {
        String sql = "SELECT * FROM Filmer";
        List<Filmer> allMovies = db.query(sql, new BeanPropertyRowMapper<>(Filmer.class));
        return allMovies;
    }

    public void deleteAllCustomers() {
        String sql = "DELETE FROM Kunde";
        db.update(sql);
    }
}
