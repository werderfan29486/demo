package com.schwarck.kundenverwaltung.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @GetMapping
    public List<String> getAllCustomers() {
        List<String> test = new ArrayList<>();
        test.add("Hello World");
        return test;
    }
}
