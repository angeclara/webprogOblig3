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

    // This is a Logger object that is used to generate error messages visible
    private Logger logger = LoggerFactory.getLogger(BilettRepsitory.class);

    // This is a Jdbc object used to make changes to a database
    @Autowired
    public JdbcTemplate db;


    // This method is used to save a customer to the Kunde table, this is done using SQL statements
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


    /* This method is used to fetch customers from the Kunde table, the fetched customers will then be added to
    an arraylist of the type Kunde, the method will return the list */
    public List<Kunde> fetchCustomers() {
        String sql = "SELECT * FROM Kunde";
        List<Kunde> allCustomers = db.query(sql, new BeanPropertyRowMapper<>(Kunde.class));

        try{

            /* Here I use the Collections' sort method and implement the comparator interface
            to sort the listed items by the etternavn */
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

    /* This method is used to fetch movies from the Filmer table, the fetched movies will then be added to
    an arraylist of the type Filmer, and return the list */
    public List<Filmer> fetchMovies() {
        String sql = "SELECT * FROM Filmer";
        List<Filmer> allMovies = db.query(sql, new BeanPropertyRowMapper<>(Filmer.class));
        return allMovies;
    }

    // This method is used to delete the items in the Kunde table.
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

    // This method returns one customer from their id
    public Kunde fetchACustomer(int id) {
        String sql = "SELECT * FROM Kunde WHERE id=?";
        Kunde customer = db.queryForObject(sql, BeanPropertyRowMapper.newInstance(Kunde.class), id);
        return customer;
    }

    // This method is used to modify the values of a customer in the Kunde table
    public void modifyCustomer(Kunde customer) {
        String sql = "UPDATE Kunde SET fornavn=?, etternavn=?, telefon=?, epost=? WHERE id=?";
        db.update(sql,customer.getFornavn(),customer.getEtternavn(), customer.getTelefon(),customer.epost, customer.getId());
    }

    // This method is used to delete a customer from the table using the specific customer id
    public void deleteCustomer(int id) {
        String sql = "DELETE FROM Kunde WHERE id=?";
        db.update(sql,id);
    }
}
