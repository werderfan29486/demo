package com.schwarck.kundenverwaltung.database;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name="dates")
@Table(name = "dates")
public class Date {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(referencedColumnName = "customer_id", nullable = false)
    private Customer customer;

    public Date() {}

    public Date(LocalDateTime date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }

    public LocalDateTime getDateTime() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return Objects.equals(id, date.id) &&
                Objects.equals(date, date.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", utilDate=" + date +
                '}';
    }

    public void setUtilDate(LocalDateTime date) {
        this.date = date;
    }
}
