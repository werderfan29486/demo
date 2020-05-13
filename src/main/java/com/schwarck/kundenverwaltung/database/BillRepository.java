package com.schwarck.kundenverwaltung.database;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BillRepository extends CrudRepository<Bill,Long> {
    List<Bill> findByCustomerId(long customerId);

    @Modifying
    @Query("delete from bill where customer.id = ?1")
    @Transactional
    void deleteAllByCustomerId(long customerId);
}
