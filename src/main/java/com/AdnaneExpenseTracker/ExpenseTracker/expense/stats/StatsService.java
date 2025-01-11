package com.AdnaneExpenseTracker.ExpenseTracker.expense.stats;

import com.AdnaneExpenseTracker.ExpenseTracker.dto.GraphDTO;
import com.AdnaneExpenseTracker.ExpenseTracker.dto.StatsDTO;

public interface StatsService {

    GraphDTO getChartData();

    StatsDTO getStats();
}
