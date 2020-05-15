package com.kundenverwaltung;

import com.schwarck.kundenverwaltung.Application;
import com.schwarck.kundenverwaltung.database.Bill;
import com.schwarck.kundenverwaltung.database.BillRepository;
import com.schwarck.kundenverwaltung.database.Date;
import com.schwarck.kundenverwaltung.service.BillService;
import com.schwarck.kundenverwaltung.service.DateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes= Application.class)
public class BillServiceTest {

    @Autowired
    BillService billService;

    @Autowired
    BillRepository billRepository;

    @Autowired
    DateService dateService;


    @Test
    public void billPayedTest() {
    billService.billPayed(6, 1);
    boolean isPayed = billRepository.findById(6L).get().isPayed();
    Assertions.assertEquals(true, isPayed);
    int count = dateService.countCustomerDates(1);
    Assertions.assertEquals(0, count);
    }

    @Test
    public void addBillTest() {
        billService.addBill(1);
    }

    @Test
    public void deleteBillTest() {
        billService.deleteBill(1, 6);
        boolean exists = billRepository.existsById(6L);
        Assertions.assertFalse(exists);
    }

    @Test
    public void deleteAllBillsFromCustomerIdTest() {
        billService.deleteAllBillsFromCustomer(1);
        List<Bill> list1 = billService.findCustomerBills(1);
        Assertions.assertEquals(0, list1.size());
    }

    @Test
    public void generateBillSumTest() {
         double billSum = billService.generateBillSum(1);
        Assertions.assertEquals(160.0, billSum);
    }

    @Test
    public void countCustomerBillsTest() {
        int count = billService.countCustomerBills(1);
        Assertions.assertEquals(0, count);
    }

    @Test
    public void deleteAllBillsTest() {
        billService.deleteAllBills();
        long count = billRepository.count();
        Assertions.assertEquals(0, count);

    }

}
