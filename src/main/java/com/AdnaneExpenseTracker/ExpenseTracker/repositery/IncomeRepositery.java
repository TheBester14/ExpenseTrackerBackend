package com.AdnaneExpenseTracker.ExpenseTracker.repositery;

import com.AdnaneExpenseTracker.ExpenseTracker.entity.Expense;
import com.AdnaneExpenseTracker.ExpenseTracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepositery extends JpaRepository<Income, Long> {
    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(i.amount) FROM Income i")
    Double sumAllamounts();

    Optional<Income> findFirstByOrderByDateDesc();
}
