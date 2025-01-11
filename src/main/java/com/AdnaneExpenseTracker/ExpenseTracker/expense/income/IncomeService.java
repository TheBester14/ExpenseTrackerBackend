package com.AdnaneExpenseTracker.ExpenseTracker.expense.income;

import com.AdnaneExpenseTracker.ExpenseTracker.dto.IncomeDTO;
import com.AdnaneExpenseTracker.ExpenseTracker.entity.Income;

import java.util.List;

public interface IncomeService {
    Income postIncome(IncomeDTO incomeDTO);
    List<IncomeDTO> getAllIncomes();
    Income updateIncome(Long id, IncomeDTO incomeDTO);
    IncomeDTO getIncomeById(Long id);
    void deleteIncome(Long id);
}
