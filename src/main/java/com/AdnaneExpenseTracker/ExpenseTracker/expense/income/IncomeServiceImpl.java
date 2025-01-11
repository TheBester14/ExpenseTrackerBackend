package com.AdnaneExpenseTracker.ExpenseTracker.expense.income;

import com.AdnaneExpenseTracker.ExpenseTracker.dto.ExpenseDTO;
import com.AdnaneExpenseTracker.ExpenseTracker.dto.IncomeDTO;
import com.AdnaneExpenseTracker.ExpenseTracker.entity.Expense;
import com.AdnaneExpenseTracker.ExpenseTracker.entity.Income;
import com.AdnaneExpenseTracker.ExpenseTracker.repositery.IncomeRepositery;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService{
    private final IncomeRepositery incomeRepositery;


    public Income postIncome(IncomeDTO incomeDTO) {
        return saveOrUpdateIncome(new Income(), incomeDTO);
    }

    private Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO) {
        income.setTitle(incomeDTO.getTitle());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());

        return incomeRepositery.save(income);
    }


    public List<IncomeDTO> getAllIncomes() {
        return incomeRepositery.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDto)
                .collect(Collectors.toList());
    }

    public Income updateIncome(Long id, IncomeDTO incomeDTO){
        Optional<Income> optionalIncome = incomeRepositery.findById(id);


        if (optionalIncome.isPresent()) {
            return saveOrUpdateIncome(optionalIncome.get(), incomeDTO);
        } else {
            throw new EntityNotFoundException("Income with id " + id + " not found");
        }
    }

    public IncomeDTO getIncomeById(Long id) {
        Optional<Income> optionalIncome = incomeRepositery.findById(id);

        if (optionalIncome.isPresent()) {
            return optionalIncome.get().getIncomeDto();
        } else {
            throw new EntityNotFoundException("Income with id " + id + " not found");
        }
    }

    public void deleteIncome(Long id) {
        Optional<Income> optionalIncome = incomeRepositery.findById(id);

        if (optionalIncome.isPresent()) {
            incomeRepositery.deleteById(id);
        } else {
            throw new EntityNotFoundException("Income with id " + id + " not found");
        }
    }


}
