package com.schwarck.kundenverwaltung.database;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "bill")
@Table(name = "bill")
public class Bill {

    @Id
    @Column(name = "bill_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "billnumber")
    private String billNumber;

    @Column(name = "payed")
    private boolean payed = false;


    private int hourlyPrice = 80;

    private static int count = 0;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bill> bills = new ArrayList<>();

    public Bill() {
    }

    public Bill(Customer customer) {
        ++count;
        this.billNumber = String.format("%03d", count);
        this.customer = customer;
        this.payed = payed;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public boolean isPayed() { return payed; }

    public void setPayed(boolean payed) { this.payed = payed; }

    public int getHourlyPrice() {
        return hourlyPrice;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public static int getCount() {
        return count;
    }

    public void setHourlyPrice(int hourlyPrice) {
        this.hourlyPrice = hourlyPrice;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public static void setCount(int count) {
        Bill.count = count;
    }



}
