package com.expense.service.consumer;

import com.expense.service.dto.ExpenseDto;
import com.expense.service.service.ExpenseService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ExpenseConsumer {

    private ExpenseService expenseService;

    @Autowired
    ExpenseConsumer(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDto expenseDto) {
        try{
            expenseService.createExpense(expenseDto);

        }catch(Exception e) {
            System.out.println("Error while processing the expense: " + e.getMessage());
        }
    }

}
