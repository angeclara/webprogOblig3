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

    // rep object!
    @Autowired
    BilettRepsitory rep;

    // This method stores the user input from the client in the database
    @PostMapping("/save")
    public void saveCustomer(Kunde innKunde, HttpServletResponse response) throws IOException {
        if (!rep.saveCustomer(innKunde)) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error in DB, pick a movie!");
        }
    }

    // This method sends a list of customers to the client
    @GetMapping("/fetch")
    public List<Kunde> fetchCustomers(HttpServletResponse response) throws IOException {
        List<Kunde> allCustomers = rep.fetchCustomers();

        if (allCustomers == null) {
            response.sendError(HttpStatus.NO_CONTENT.value(),
                    "No tickets to fetch!");
        }
        return allCustomers;
    }

    // This method sends a list of movies to the client
    @GetMapping("/fetchMovies")
    public List<Filmer> fetchMovies() {
        return rep.fetchMovies();
    }

    // This method deletes the items of the Kunde table
    @GetMapping("/delete")
    public void deleteCustomers(HttpServletResponse response) throws IOException {
        List<Kunde> allCustomers = rep.fetchCustomers();
        if (!rep.deleteAllCustomers()) {
            response.sendError(HttpStatus.NO_CONTENT.value(), "No tickets to delete!");
        }
    }

    // This method is used to modify the customers information
    @PostMapping("/modify")
    public void modifyCustomer(Kunde customer) {
        rep.modifyCustomer(customer);
    }

    // this method is used to fetch one custommer
    @GetMapping("/fetchCustomer")
    public Kunde fetchCustomer(int id) {
        return rep.fetchACustomer(id);
    }

    // This method is used to delete a specifc customer
    @GetMapping("/deleteCustomer")
    public void deleteCustomer(int id) {
        rep.deleteCustomer(id);
    }
}
