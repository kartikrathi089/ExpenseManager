package com.expense.service.repoistory;

import com.expense.service.entities.Expense;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExpenseReposistory extends CrudRepository<Expense, Long> {

    List<Expense> findByUserId(String userId);

    List<Expense> findByUserIdAndCreatedBetween(String userId, Timestamp startTime, Timestamp endTime);

    Optional<Expense> findByUserIdandExternalId(String userId, String externalId);
}
