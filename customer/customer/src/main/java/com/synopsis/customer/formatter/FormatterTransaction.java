package com.synopsis.customer.formatter;

import com.synopsis.customer.models.Transaction;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class FormatterTransaction {
    public static List<Transaction> formatter(JsonNode transactions){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Transaction> transactionList = null;
        try {
            transactionList = objectMapper.convertValue(transactions,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Transaction.class)
            );
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return transactionList;
    }
}