package com.AdnaneExpenseTracker.ExpenseTracker.dto;

import com.AdnaneExpenseTracker.ExpenseTracker.entity.Expense;
import com.AdnaneExpenseTracker.ExpenseTracker.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {
    private List<Expense> expenseList;

    private List<Income> incomeList;


}
