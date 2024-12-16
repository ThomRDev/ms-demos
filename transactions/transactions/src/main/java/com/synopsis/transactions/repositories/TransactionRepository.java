package com.synopsis.transactions.repositories;

import com.synopsis.transactions.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long> {

    @Query(value = "SELECT * FROM public.get_transaction_by_account_iban(:iban)", nativeQuery = true)
    List<Transactions> getTransactionsByIban(@Param("iban") String iban);

    @Query(value = "SELECT insert_transaction(:in_reference, :in_account_iban, :in_ammount, :in_description, :in_status, :in_channel)", nativeQuery = true)
    void insertTransaction(String in_reference, String in_account_iban, Double in_ammount, String in_description, String in_status, String in_channel);
}
