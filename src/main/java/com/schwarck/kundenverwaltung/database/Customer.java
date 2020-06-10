package com.schwarck.kundenverwaltung.database;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Entity(name = "customer")
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column (name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Customernumber")
    private String customerNumber;

    @Column(name = "Name")
    private String name;

    @Column(name = "Vorname")
    private String firstName;

    @Column(name = "Straße")
    private String street;

    @Column(name = "Hausnummer")
    private String houseNumber;

    @Column(name = "Postleitzahl")
    private String postalCode;

    @Column(name = "EMail")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Date> dates = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bill> bills = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name, String firstName, String street, String houseNumber, String postalCode, String email, String phoneNumber) {
        this.name = name;
        this.firstName = firstName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getId() {return id;}

    public String getCustomerNumber() {return customerNumber;}

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEMail() {
        return email;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String eMail) {
        this.email = eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return //id.equals(customer.id) &&
                Objects.equals(customerNumber, customer.customerNumber) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(street, customer.street) &&
                Objects.equals(houseNumber, customer.houseNumber) &&
                Objects.equals(postalCode, customer.postalCode) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerNumber, name, firstName, street, houseNumber, postalCode, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", customerNumber='" + customerNumber + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + email + '\'' +
                ", eMail='" + phoneNumber + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }
}

