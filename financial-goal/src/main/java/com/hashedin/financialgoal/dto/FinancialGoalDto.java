package com.hashedin.financialgoal.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class FinancialGoalDto {

    @NotEmpty(message = "Goal name cannot be empty")
    private String goalName;

    @NotNull(message = "Total amount cannot be null")
    @Positive(message = "Total amount must be positive")
    private double totalAmount;

    @NotNull(message = "Entered amount cannot be null")
    @PositiveOrZero(message = "Entered amount must be positive or zero")
    @DecimalMax(value = "9999999999.99", message = "Entered amount must not exceed 9999999999.99")
    private double enteredAmount;

    @NotNull(message = "Start date cannot be null")
    @FutureOrPresent(message = "Start date must be in the present or future")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be null")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    @NotEmpty(message = "User ID cannot be empty")
    private String userId;
}
