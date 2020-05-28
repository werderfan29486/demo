package com.schwarck.kundenverwaltung.api;

import com.schwarck.kundenverwaltung.database.Customer;
import com.schwarck.kundenverwaltung.database.CustomerRepository;
import com.schwarck.kundenverwaltung.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "/customeradd", method = RequestMethod.POST)
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }
}
