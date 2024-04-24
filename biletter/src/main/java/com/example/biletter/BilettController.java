package com.example.biletter;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class BilettController {
    @Autowired
    BilettRepsitory rep;

    @PostMapping("/save")
    public void saveCustomer(Kunde innKunde, HttpServletResponse response) throws IOException {
        if (!rep.saveCustomer(innKunde)) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error in DB, pick a movie! An account can only have one number!");
        }
    }

    @GetMapping("/fetch")
    public List<Kunde> fetchCustomers(HttpServletResponse response) throws IOException {
        List<Kunde> allCustomers = rep.fetchCustomers();

        if (allCustomers == null) {
            response.sendError(HttpStatus.NO_CONTENT.value(),
                    "No tickets to fetch!");
        }
        return allCustomers;
    }

    @GetMapping("/fetchMovies")
    public List<Filmer> fetchMovies() {
        return rep.fetchMovies();
    }

    @GetMapping("/delete")
    public void  deleteCustomers(HttpServletResponse response) throws IOException {
        List<Kunde> allCustomers = rep.fetchCustomers();

        if (allCustomers == null) {
            response.sendError(HttpStatus.NO_CONTENT.value(), "No tickets to delete!");
        }
        rep.deleteAllCustomers();
    }
}
