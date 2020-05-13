package com.kundenverwaltung;

import com.schwarck.kundenverwaltung.Application;
import com.schwarck.kundenverwaltung.database.Customer;
import com.schwarck.kundenverwaltung.database.Date;
import com.schwarck.kundenverwaltung.database.DateRepository;
import com.schwarck.kundenverwaltung.service.DateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest(classes= Application.class)
public class DateServiceTest {

    @Autowired
    DateService dateService;

    @Autowired
    DateRepository dateRepository;

    LocalDateTime date = LocalDateTime.of(2020, 10, 10, 10, 10);
    LocalDateTime date2 = LocalDateTime.of(2019, 9, 8, 9, 10);

    @Test
    public void addDateTest() {
        dateService.addDate(1, date);
        dateService.addDate(1, date);
        dateService.addDate(2, date);
        boolean dateExists = dateRepository.findById(1L).isPresent();
        Assertions.assertTrue(dateExists);
    }

    @Test
    public void deleteDateTest() {
        dateService.deleteDate(1, 4);
        boolean dateExists = dateRepository.findById(4L).isPresent();
        Assertions.assertFalse(dateExists);
    }

    @Test
    public void changeDateTest() {
        dateService.changeDate(1, 4, date2);
        LocalDateTime date = dateRepository.findById(5L).get().getDateTime();
        Assertions.assertEquals(date2, date);
        }

    @Test
    public void deleteAllCustomerDatesTest() {
        dateService.deleteAllCustomerDates();
        long empty = dateRepository.count();
        Assertions.assertEquals(0, empty);
    }

    @Test
    public void deleteAllDatesFromCustomerTest() {
        dateService.deleteAllDatesFromCustomer(2);
        int count = dateService.countCustomerDates(2);
        Assertions.assertEquals(0, count);
    }

    @Test
    public void findCustomerDatesTest() {
        List<Date> list1 = dateService.findCustomerDates(1);
        Assertions.assertEquals(2, list1.size());
    }

    @Test
    public void countCustomerDatesTest() {
        int count = dateService.countCustomerDates(1);
        Assertions.assertEquals(2, count);
    }
}
