package com.hashedin.budgetexpense.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ExpenseDto {

	@NotNull(message = "Expense amount must not be null")
	@Positive(message = "Expense amount must be a positive number")
	private Double expenseAmount;

	@NotNull(message = "Category ID must not be null")
	private Integer categoryId;

	@NotNull(message = "User ID must not be null")
	private Integer userId;

	@NotNull(message = "Budget ID must not be null")
	private Integer budgetId;
}
