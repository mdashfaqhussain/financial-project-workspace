package com.hashedin.financialgoal.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GoalAddAmountDto {
	@NotNull(message = "Goal ID cannot be null")
    @Positive(message = "Goal ID must be a positive number")
    private Integer goalId;

    @NotNull(message = "Expense amount cannot be null")
    @Positive(message = "Expense amount must be a positive number")
    @DecimalMax(value = "9999999999.99", message = "Expense amount must not exceed 9999999999.99")
    private Double expenseAmount;

}
