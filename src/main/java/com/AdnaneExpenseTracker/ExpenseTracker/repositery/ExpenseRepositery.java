package com.AdnaneExpenseTracker.ExpenseTracker.repositery;

import com.AdnaneExpenseTracker.ExpenseTracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepositery extends JpaRepository<Expense, Long> {

}
