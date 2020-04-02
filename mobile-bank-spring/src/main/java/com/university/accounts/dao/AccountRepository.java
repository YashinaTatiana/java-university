package com.university.accounts.dao;

import com.university.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Modifying
    @Query("UPDATE Account e SET e.amount = :amount WHERE e.id = :id")
    void setAccountAmount(@Param("id") Long id, @Param("amount") BigDecimal amount);
}
