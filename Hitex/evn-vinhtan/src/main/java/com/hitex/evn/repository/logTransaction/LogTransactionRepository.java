package com.hitex.evn.repository.logTransaction;


import com.hitex.evn.model.logTransaction.LogTransactionBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogTransactionRepository extends CrudRepository<LogTransactionBase, Integer> {
}
