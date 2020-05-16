package com.kundenverwaltung;

import com.schwarck.kundenverwaltung.Application;
import com.schwarck.kundenverwaltung.database.Customer;
import com.schwarck.kundenverwaltung.database.CustomerRepository;
import com.schwarck.kundenverwaltung.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes= Application.class)
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    Customer customer1 = new Customer("Gantzert", "Sebastian", "Auf der Schmelz", "67", "64380", "0151/23456", "sega@sega.de");
    Customer customer2 = new Customer("Weygandt", "Martin", "Egerländer", "44", "64380", "0151/62748294", "weygandt@martin.de");

    @Test
    public void addCustomerTest() {
        customerService.addCustomer(customer1);
        customerService.addCustomer(customer2);
        List<Customer> list1 = customerService.getAllCustomers();
        Assertions.assertTrue(list1.contains(customer2));
    }

    @Test
    public void deleteCustomerTest() {
        //customerService.addCustomer(customer1);
        customerService.deleteCustomer(1);
        List<Customer> list1 = customerService.getAllCustomers();
        Assertions.assertFalse(list1.contains(customer1));
    }

    @Test
    public void findCustomerTest() {
        customerService.findCustomer(1);
        List<Customer> list1 = customerService.getAllCustomers();
        Assertions.assertTrue(list1.contains(customer1));
    }

    @Test
    public void updateCustomerNameTest() {
        customerService.updateCustomer(1, "Gantzert", "Schwarck");
        Customer customer = customerService.findCustomer(1);
        Assertions.assertEquals(customer.getName(), "Schwarck");
    }

    @Test
    public void deleteAllTest() {
        customerRepository.deleteAll();
        Assertions.assertEquals(0, customerRepository.count());
    }


}
