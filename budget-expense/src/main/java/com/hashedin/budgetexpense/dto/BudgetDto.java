package com.hashedin.budgetexpense.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertFalse.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class BudgetDto {

	@NotBlank(message = "Budget name is required")
    private String budgetName;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @PositiveOrZero(message = "Allocated amount must be a positive number or zero")
    private double budgetAllocatedAmount;


    @NotNull(message = "User ID is required")
    private Integer userId;

   

}
