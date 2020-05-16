package com.schwarck.kundenverwaltung.service;

import com.schwarck.kundenverwaltung.database.*;
import com.schwarck.kundenverwaltung.exceptions.BillDoesNotExistException;
import com.schwarck.kundenverwaltung.exceptions.CustomerDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class BillService {

    private final BillRepository billRepository;
    private final CustomerRepository customerRepository;
    private final DateRepository dateRepository;
    private final DateService dateService;

    @Autowired
    public BillService(BillRepository billRepository, CustomerRepository customerRepository, DateRepository dateRepository, DateService dateService) {
        this.billRepository = billRepository;
        this.customerRepository = customerRepository;
        this.dateRepository = dateRepository;
        this.dateService = dateService;
    }

    Bill bill1 = new Bill();
    Logger logger = Logger.getLogger("");

    public void billPayed(long billId, long customerId) {
        try {
            billRepository.findById(billId).orElseThrow(BillDoesNotExistException::new);
            customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            dateRepository.deleteAllByCustomerId(customerId);
            Bill bill = billRepository.findById(billId).get();
            bill.setPayed(true);
            billRepository.save(bill);
        }
        catch (BillDoesNotExistException | CustomerDoesNotExistException e) {
            logger.warning("Rechnung konnte nicht gefunden werden");
        }
    }

    public void addBill(long customerId) {
        try {
            Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            billRepository.save(new Bill(customer));
        }
        catch (CustomerDoesNotExistException e) {
            logger.warning("Rechnung konnte nicht hinzugefügt werden");
        }
    }

    public void deleteBill(long customerId, long billId) {
        try {
            customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            billRepository.findById(billId).orElseThrow(BillDoesNotExistException::new);
            billRepository.deleteById(billId);
        }
        catch (CustomerDoesNotExistException | BillDoesNotExistException e) {
            logger.warning("Rechnung konnte nicht gelöscht werden");
        }
    }

    public void deleteAllBills() {
        billRepository.deleteAll();
    }

    public void deleteAllBillsFromCustomer(long customerId) {
        try {
            customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            billRepository.deleteAllByCustomerId(customerId);
        }
        catch (CustomerDoesNotExistException e) {
            logger.warning("Rechnung konnte nicht hinzugefügt werden");
        }
    }

    public double generateBillSum(long customerId) {
        int countDates = dateService.countCustomerDates(customerId);
        return countDates * bill1.getHourlyPrice();
    }

    public int countCustomerBills(long customerId) {
        int customerBillsCounted = 0;
        try {
            customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            customerBillsCounted = billRepository.findByCustomerId(customerId).size();
        }

        catch (CustomerDoesNotExistException e) {
            logger.warning("Rechnungen konnten nicht gefunden werden");
        }
        return customerBillsCounted;
    }

    public List<Bill> findCustomerBills(long customerId) {
        List<Bill> list1 = new ArrayList<>();
        try {
            customerRepository.findById(customerId).orElseThrow(CustomerDoesNotExistException::new);
            list1 = billRepository.findByCustomerId(customerId);
        }

        catch (CustomerDoesNotExistException e) {
            logger.warning("Termine konnten nicht gefunden werden");
        }
        return list1;
    }

}
