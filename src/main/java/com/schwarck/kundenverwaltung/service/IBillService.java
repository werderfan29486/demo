package com.schwarck.kundenverwaltung.service;

public interface IBillService {
    public void addBill(long customerId);
    public double generateBillSum(long customerId);
    public void deleteBill(long customerId, long billId);
    public void deleteAllBills();
    public void deleteAllBillsFromCustomer(long customerId);
}
