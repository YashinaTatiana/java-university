package com.university.accounts.dao;

import com.university.accounts.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("SELECT e from Operation e " +
                  "WHERE ((e.senderId IN :accounts AND type = 'TRANSFER') " +
                  "OR (e.recipientId IN :accounts AND (type = 'RECEIPTS' OR type = 'REFILL')))")
    List<Operation> getUserOperations(@Param("accounts") List<Long> accounts);
}
