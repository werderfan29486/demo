package com.schwarck.kundenverwaltung.service;

import com.schwarck.kundenverwaltung.database.Customer;
import com.schwarck.kundenverwaltung.database.CustomerRepository;
import com.schwarck.kundenverwaltung.exceptions.CustomerDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    Logger logger = LoggerFactory.getLogger("");

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void addCustomer(Customer customer) {
        Long id = customerRepository.save(customer).getId();
        customer.setCustomerNumber(String.format("%04d", id));
    }

    public void deleteCustomer(long id) {
        try {
            customerRepository.findById(id).orElseThrow(CustomerDoesNotExistException::new);
            Customer customer = customerRepository.findById(id).get();
            customerRepository.delete(customer);
            } catch (CustomerDoesNotExistException e) {
            logger.warn("Löschvorgang nicht möglich");
            }
        }

    public void updateCustomer(long id, String oldValue, String newValue) {

        try {

            customerRepository.findById(id).orElseThrow(CustomerDoesNotExistException::new);

            Customer customer = customerRepository.findById(id).get();

            if (customer.getName().equals(oldValue)) {
                customer.setName(newValue);
            } else if (customer.getFirstName().equals(oldValue)) {
                customer.setFirstName(newValue);
            } else if (customer.getStreet().equals(oldValue)) {
                customer.setStreet(newValue);
            } else if (customer.getHouseNumber().equals(oldValue)) {
                customer.setHouseNumber(newValue);
            } else if (customer.getPostalCode().equals(oldValue)) {
                customer.setPostalCode(newValue);
            } else if (customer.getEMail().equals(oldValue)) {
                customer.setEmail(newValue);
            } else if (customer.getPhoneNumber().equals(oldValue)) {
                customer.setPhoneNumber(newValue);
            }
            customerRepository.save(customer);
        } catch (CustomerDoesNotExistException e) {
            logger.warn("Updatevorgang nicht möglich");
        }
   }

    public Customer findCustomer(long id) {
        Customer customer = new Customer();
        try {
            customerRepository.findById(id).orElseThrow(CustomerDoesNotExistException::new);
            customer = customerRepository.findById(id).get();
        } catch (CustomerDoesNotExistException e) {
            logger.warn("Kunde konnte nicht gefunden werden");
        }
        return customer;
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }


}
