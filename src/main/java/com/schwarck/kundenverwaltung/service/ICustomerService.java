package com.schwarck.kundenverwaltung.service;

import com.schwarck.kundenverwaltung.database.Customer;
import com.schwarck.kundenverwaltung.exceptions.CustomerDoesNotExistException;

public interface ICustomerService {

    public void addCustomer(Customer customer);
    public void deleteCustomer(long id) throws CustomerDoesNotExistException;
    public void updateCustomer(long id, String oldValue, String newValue);
    public Customer findCustomer(long id);
    public void deleteAll();

}
