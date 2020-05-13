package com.schwarck.kundenverwaltung.database;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DateRepository extends CrudRepository<Date, Long> {
    List<Date> findByCustomerId(long customerId);

    @Modifying
    @Query("delete from dates where customer.id = ?1")
    @Transactional
    void deleteAllByCustomerId(long customerId);
}
