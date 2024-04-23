package com.example.biletter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BilettController {
    private final List<Kunde> customerList = new ArrayList<Kunde>();

    @PostMapping("/load")
    public void loadCustomer(Kunde innKunde) {
        customerList.add(innKunde);
    }

    @GetMapping("/getAll")
    public List<Kunde> getAll() {
        return customerList;
    }

    @PostMapping("/delete")
    public void  deleteCustomers() {
        customerList.clear();
    }

    @GetMapping("/deleted")
    public List<Kunde> deleteAll() {
        return customerList;
    }
}
