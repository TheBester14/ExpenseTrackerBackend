package com.AdnaneExpenseTracker.ExpenseTracker.expense;

import com.AdnaneExpenseTracker.ExpenseTracker.dto.ExpenseDTO;
import com.AdnaneExpenseTracker.ExpenseTracker.entity.Expense;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.AdnaneExpenseTracker.ExpenseTracker.repositery.ExpenseRepositery;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepositery expenseRepositery;




    public Expense postExpense(ExpenseDTO expenseDTO) {
        return saveOrUpdateExpense(new Expense(), expenseDTO);
    }

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO) {
        expense.setTitle(expenseDTO.getTitle());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());
        return expenseRepositery.save(expense);
    }

    public Expense updateExpense(Long id, ExpenseDTO expenseDTO) {
        Optional<Expense> optionalExpense = expenseRepositery.findById(id);

        if (optionalExpense.isPresent()) {
            return saveOrUpdateExpense(optionalExpense.get(), expenseDTO);
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " not found");
        }
    }

    public List<Expense> getAllExpenses() {
        return expenseRepositery.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    public Expense getExpenseById(Long id) {
        Optional<Expense> optionalExpense = expenseRepositery.findById(id);

        if (optionalExpense.isPresent()) {
            return optionalExpense.get();
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " not found");
        }
    }

    public void deleteExpense(Long id) {
        Optional<Expense> optionalExpense = expenseRepositery.findById(id);

        if (optionalExpense.isPresent()) {
            expenseRepositery.deleteById(id);
        } else {
            throw new EntityNotFoundException("Expense with id " + id + " not found to delete");
        }
    }
}
