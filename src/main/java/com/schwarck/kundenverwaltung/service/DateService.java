package com.schwarck.kundenverwaltung.service;

import com.schwarck.kundenverwaltung.database.Customer;
import com.schwarck.kundenverwaltung.database.CustomerRepository;
import com.schwarck.kundenverwaltung.database.Date;
import com.schwarck.kundenverwaltung.database.DateRepository;
import com.schwarck.kundenverwaltung.exceptions.CustomerDoesNotExistException;
import com.schwarck.kundenverwaltung.exceptions.DateDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class DateService {

    private final DateRepository dateRepository;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Autowired
    public DateService(DateRepository dateRepository, CustomerService customerService, CustomerRepository customerRepository) {
        this.dateRepository = dateRepository;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    Logger logger = Logger.getLogger("");

    public void addDate(long customerId, LocalDateTime date) {
        Customer customer = customerService.findCustomer(customerId);

        try {
        customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            dateRepository.save(new Date(date, customer));
        }
        catch(CustomerDoesNotExistException e){
            logger.warning("Termin konnte nicht hinzugefügt werden");
        }
    }

    public void deleteDate(long customerId, long dateId) {
        try {
            customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            dateRepository.findById(dateId).orElseThrow(DateDoesNotExistException::new);
            dateRepository.deleteById(dateId);
        }
        catch (CustomerDoesNotExistException | DateDoesNotExistException e) {
            logger.warning("Termin kann nicht gelöscht werden");
        }
    }

    public void deleteAllCustomerDates() {
        dateRepository.deleteAll();
    }

    public void deleteAllDatesFromCustomer(long customerId) {
        try {
            customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            dateRepository.deleteAllByCustomerId(customerId);
        }
        catch (CustomerDoesNotExistException e) {
            logger.warning("Termine konnten nicht gelöscht werden");
        }
    }

    public void changeDate(long customerId, long dateId, LocalDateTime newDate) {
            try {
                customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
                dateRepository.findById(dateId).orElseThrow(DateDoesNotExistException::new);
                dateRepository.deleteById(dateId);
                addDate(customerId, newDate);
            }

            catch (CustomerDoesNotExistException | DateDoesNotExistException e) {
                logger.warning("Termin konnte nicht geändert werden");
            }
    }

    public List<Date> findCustomerDates(long customerId) {
        List<Date> list1 = new ArrayList<>();
        try {
            customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            list1 = dateRepository.findByCustomerId(customerId);
        }

        catch (CustomerDoesNotExistException e) {
            logger.warning("Termine konnten nicht gefunden werden");
        }
        return list1;
    }

    public int countCustomerDates(long customerId) {
        int customerDatesCounted = 0;
        try {
            customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            customerDatesCounted = dateRepository.findByCustomerId(customerId).size();
        }

        catch (CustomerDoesNotExistException e) {
            logger.warning("Termine konnten nicht gefunden werden");
        }
        return customerDatesCounted;
    }


}
