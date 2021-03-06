package com.schwarck.kundenverwaltung.api;

import com.schwarck.kundenverwaltung.database.Customer;
import com.schwarck.kundenverwaltung.database.CustomerRepository;
import com.schwarck.kundenverwaltung.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(value="/customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(value="/customers/{id}", method = RequestMethod.GET)
    public Customer findCustomerById(@PathVariable("id") long id) {
        return customerService.findCustomer(id);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }

    @DeleteMapping("/customers/{id}")
    public List<Customer> deleteCustomer(@PathVariable("id") long id) {
            customerService.deleteCustomer(id);
            return customerService.getAllCustomers();
    }

    @DeleteMapping("/customers")
    public List<Customer> deleteAllCustomers() {
        customerService.deleteAll();
        return customerService.getAllCustomers();
    }

    @PutMapping("/customers/{id}")
    public void updateName(@PathVariable("id") long id, @RequestBody Customer customer) {
        LOGGER.info("Getting request for customer with value: "+customer.getName());
        Customer updatedCustomer = customerService.findCustomer(id);
        updatedCustomer.setName(customer.getName());
        customerService.saveCustomer(updatedCustomer);
    }

}

