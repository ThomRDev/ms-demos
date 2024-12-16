package com.synopsis.customer.repositories;

import com.synopsis.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT update_customer_name(:inId, :inCode, :inName, :inPhone, :inIban, :inSurname, :inAddress)", nativeQuery = true)
    void updateCustomerName(
            @Param("inId") Long inId,
            @Param("inCode") String inCode,
            @Param("inName") String inName,
            @Param("inPhone") String inPhone,
            @Param("inIban") String inIban,
            @Param("inSurname") String inSurname,
            @Param("inAddress") String inAddress
    );
}