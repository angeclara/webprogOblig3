package com.example.biletter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BilettController {
    @Autowired
    BilettRepsitory rep;

    @PostMapping("/save")
    public void saveCustomer(Kunde innKunde) {
        rep.saveCustomer(innKunde);
    }

    @GetMapping("/fetch")
    public List<Kunde> fetchCustomers() {
        return rep.fetchCustomers();
    }

    @GetMapping("/fetchMovies")
    public List<Filmer> fetchMovies() {
        return rep.fetchMovies();
    }

    @PostMapping("/delete")
    public void  deleteCustomers() {
        rep.deleteAllCustomers();
    }
}
