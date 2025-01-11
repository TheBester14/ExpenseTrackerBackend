package com.AdnaneExpenseTracker.ExpenseTracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data

public class ExpenseDTO {

    // DTO = DATA TRANSFER OBJECT
    // The datatype of what will be sent
    private String title;
    private String description;
    private String category;
    private LocalDate date;
    private Integer amount;
}
