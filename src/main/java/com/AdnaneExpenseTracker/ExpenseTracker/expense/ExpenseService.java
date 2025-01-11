package com.AdnaneExpenseTracker.ExpenseTracker.expense;

import com.AdnaneExpenseTracker.ExpenseTracker.dto.ExpenseDTO;
import com.AdnaneExpenseTracker.ExpenseTracker.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpenses();

    Expense getExpenseById(Long id);

    Expense updateExpense(Long id, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);
}
