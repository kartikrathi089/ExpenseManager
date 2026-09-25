package com.expense.service.service;

import com.expense.service.dto.ExpenseDto;
import com.expense.service.entities.Expense;
import com.expense.service.repoistory.ExpenseReposistory;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExpenseService {

    private ExpenseReposistory expenseReposistory;

    private ObjectMapper objectMapper=new ObjectMapper();

    @Autowired
    ExpenseService(ExpenseReposistory expenseReposistory){
        this.expenseReposistory=expenseReposistory;
    }

    public boolean createExpense(ExpenseDto expenseDto){
        setCurrency(expenseDto);
        try{
            expenseReposistory.save(objectMapper.convertValue(expenseDto, Expense.class));

        }catch(Exception ex){
            return false;
        }

        return true;

    }

    public boolean updateExpense(ExpenseDto expenseDto){
        setCurrency(expenseDto);
        Optional<Expense> expenseFoundOpt = expenseReposistory.findByUserIdandExternalId(expenseDto.getUserId(), expenseDto.getExternalId());
        if(expenseFoundOpt.isEmpty()){
            return false;
        }
        Expense expense = expenseFoundOpt.get();
        expense.setAmount(expenseDto.getAmount());
        expense.setMerchant(Strings.isNotBlank(expenseDto.getMerchant()) ? expenseDto.getMerchant() : expense.getMerchant());
        expense.setCurrency(Strings.isNotBlank(expenseDto.getCurrency()) ? expenseDto.getCurrency() : expense.getCurrency());
        expenseReposistory.save(expense);
        return true;

    }

    public List<ExpenseDto> getExpenses(String userId){
        List<Expense> expenseOpt = expenseReposistory.findByUserId(userId);
        return objectMapper.convertValue(expenseOpt, new TypeReference<List<ExpenseDto>>() {});
    }

    private void setCurrency(ExpenseDto expenseDto){
        if(Objects.isNull(expenseDto.getCurrency())){
            expenseDto.setCurrency("inr");
        }
    }
}
