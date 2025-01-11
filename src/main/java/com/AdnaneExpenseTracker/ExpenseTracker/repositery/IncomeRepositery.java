package com.AdnaneExpenseTracker.ExpenseTracker.repositery;

import com.AdnaneExpenseTracker.ExpenseTracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepositery extends JpaRepository<Income, Long> {
}
