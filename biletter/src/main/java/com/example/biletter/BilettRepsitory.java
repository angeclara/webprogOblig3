package com.example.biletter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class BilettRepsitory {

    private Logger logger = LoggerFactory.getLogger(BilettRepsitory.class);

    @Autowired
    public JdbcTemplate db;

    public boolean saveCustomer(Kunde kunde) {
        String sql = "INSERT INTO Kunde(fornavn, etternavn, telefon, epost, film, antall)";
        sql += "VALUES (?, ?, ?, ?, ?, ?)";
        try{
            db.update(sql, kunde.getFornavn(), kunde.getEtternavn(), kunde.getTelefon(), kunde.getEpost(),
                    kunde.getFilm(), kunde.getAntall());
            return true;
        }
        catch (Exception e) {
            logger.error("Error in saveCustomer : " + e);
            return false;
        }
    }

    public List<Kunde> fetchCustomers() {
        String sql = "SELECT * FROM Kunde";
        List<Kunde> allCustomers = db.query(sql, new BeanPropertyRowMapper<>(Kunde.class));
        try{
            Collections.sort(allCustomers, new Comparator<Kunde>() {
                @Override
                public int compare(Kunde k1, Kunde k2) {
                    return k1.getEtternavn().compareTo(k2.getEtternavn());
                }
            });
            return allCustomers;
        }
        catch (Exception e) {
            logger.error("Error in fetchCustomers : " + e);
            return null;
        }
    }

    public List<Filmer> fetchMovies() {
        String sql = "SELECT * FROM Filmer";
        List<Filmer> allMovies = db.query(sql, new BeanPropertyRowMapper<>(Filmer.class));
        return allMovies;
    }

    public boolean deleteAllCustomers() {
        String sql = "DELETE FROM Kunde";
        try {
            db.update(sql);
            return true;
        }
        catch (Exception e) {
            logger.error("Error in deleteAllCustomers : " + e);
            return false;
        }
    }
}
