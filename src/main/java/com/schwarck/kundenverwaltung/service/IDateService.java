package com.schwarck.kundenverwaltung.service;

import java.time.LocalDateTime;

public interface IDateService {

    public void addDate(long id, LocalDateTime date);
    public void deleteDate(long customerId, long dateId);
    public void changeDate(long customerId, long dateId, LocalDateTime newDate);
    public void deleteAllCustomerDates();
    public void deleteAllDatesFromCustomer(long customerId);

}
