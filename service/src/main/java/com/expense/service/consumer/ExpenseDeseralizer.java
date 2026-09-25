package com.expense.service.consumer;

import com.expense.service.dto.ExpenseDto;
import org.apache.kafka.common.serialization.Deserializer;
import tools.jackson.databind.ObjectMapper;

public class ExpenseDeseralizer implements Deserializer<ExpenseDto> {

   @Override
    public void close() {
    }

    @Override
    public void configure(java.util.Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public ExpenseDto deserialize(String topic, byte[] arg1) {
        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDto expenseDto =null;
        try{
            expenseDto=objectMapper.readValue(arg1,ExpenseDto.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return expenseDto;
    }

}
