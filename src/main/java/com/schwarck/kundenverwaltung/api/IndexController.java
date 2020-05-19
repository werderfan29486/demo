package com.schwarck.kundenverwaltung.api;

import com.schwarck.kundenverwaltung.database.Customer;
import com.schwarck.kundenverwaltung.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public List<Customer> getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return customers;
    }
}