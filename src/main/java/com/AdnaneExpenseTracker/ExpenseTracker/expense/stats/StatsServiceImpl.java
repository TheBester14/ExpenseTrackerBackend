package com.AdnaneExpenseTracker.ExpenseTracker.expense.stats;

import com.AdnaneExpenseTracker.ExpenseTracker.dto.GraphDTO;
import com.AdnaneExpenseTracker.ExpenseTracker.dto.StatsDTO;
import com.AdnaneExpenseTracker.ExpenseTracker.entity.Expense;
import com.AdnaneExpenseTracker.ExpenseTracker.entity.Income;
import com.AdnaneExpenseTracker.ExpenseTracker.repositery.ExpenseRepositery;
import com.AdnaneExpenseTracker.ExpenseTracker.repositery.IncomeRepositery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService{

    private final IncomeRepositery incomeRepositery;

    private final ExpenseRepositery expenseRepositery;

    public GraphDTO getChartData() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

        GraphDTO graphDTO = new GraphDTO();
        graphDTO.setExpenseList(expenseRepositery.findByDateBetween(startDate, endDate));
        graphDTO.setIncomeList(incomeRepositery.findByDateBetween(startDate, endDate));

        return graphDTO;
    }

    public StatsDTO getStats() {
        Double totalIncome = incomeRepositery.sumAllamounts();
        Double totalExpense = expenseRepositery.sumAllamounts();

        Optional<Income> optionalIncome = incomeRepositery.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense = expenseRepositery.findFirstByOrderByDateDesc();

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        statsDTO.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);


        // TO get remaining balance
        statsDTO.setBalance(totalIncome - totalExpense);

        // To get min and max incomes, expense
        List<Income> incomeList = incomeRepositery.findAll();
        List<Expense> expenseList = expenseRepositery.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        // MAX AND MIN INCOMES
        statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);

        statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble(): null);
        statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble(): null);

        return statsDTO;

    }


}
