package com.hashedin.financialgoal.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.financialgoal.dto.FinancialGoalDto;
import com.hashedin.financialgoal.dto.GoalAddAmountDto;
import com.hashedin.financialgoal.service.IFinancialGoalService;
import com.hashedin.financialgoal.utility.ResponseModel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/goal")
@Validated
@Slf4j
public class FinancialGoalRepository {

    @Autowired
    private IFinancialGoalService financialGoalService;

    @Operation(
            tags = "New Financial Goal",
            description = "Create a new financial goal",
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "Success"
            )
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseModel> saveGoal(@Valid @RequestBody FinancialGoalDto goalDto) {
        return financialGoalService.saveGoal(goalDto);
    }

    @Operation(
            tags = "Financial Goal Deletion",
            description = "Delete a financial goal by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted the financial goal"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Financial goal not found"
                    )
            }
    )
    @DeleteMapping("/delete/{goalId}")
    public ResponseEntity<ResponseModel> deleteFinancialGoal(@PathVariable int goalId) {
        return financialGoalService.deleteFinancialGoal(goalId);
    }

//    @Operation(
//            tags = "All Financial Goals",
//            description = "Get all financial goals",
//            responses = @ApiResponse(
//                    responseCode = "200",
//                    description = "Success"
//            )
//    )
//    @GetMapping("/all")
//    public ResponseEntity<ResponseModel> getAllFinancialGoals() {
//        return financialGoalService.getAllFinancialGoals();
//    }

    @Operation(
            tags = "Update Financial Goal",
            description = "Update an existing financial goal",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated the financial goal"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Financial goal not found"
                    )
            }
    )
    @PutMapping("/update/{goalId}")
    public ResponseEntity<ResponseModel> updateFinancialGoal(@PathVariable int goalId, @Valid @RequestBody FinancialGoalDto financialGoalDto) {
        return financialGoalService.updateFinancialGoal(goalId, financialGoalDto);
    }

    @Operation(
            tags = "Get Financial Goals by User ID",
            description = "Get financial goals by user ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved financial goals by user ID"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No financial goals found for the user ID"
                    )
            }
    )
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseModel> getFinancialGoalsByUserId(@PathVariable int userId) {
    	log.info("Fetching financial goals for user ID: {}", userId);
        return financialGoalService.getFinancialGoalsByUserId(userId);
    }

    @Operation(
            tags = "Get Financial Goals by Start Date",
            description = "Get financial goals by start date",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved financial goals by start date"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data"
                    )
            }
    )
    @GetMapping("/startDate/{startDate}")
    public ResponseEntity<ResponseModel> getFinancialGoalsByStartDate(@PathVariable @Valid @NotEmpty LocalDate startDate) {
        return financialGoalService.getFinancialGoalsByStartDate(startDate);
    }

    @Operation(
            tags = "Get Financial Goals by End Date",
            description = "Get financial goals by end date",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved financial goals by end date"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data"
                    )
            }
    )
    @GetMapping("/endDate/{endDate}")
    public ResponseEntity<ResponseModel> getFinancialGoalsByEndDate(@PathVariable @Valid @NotEmpty LocalDate endDate) {
        return financialGoalService.getFinancialGoalsByEndDate(endDate);
    }

    @Operation(
            tags = "Get Financial Goals Between Dates",
            description = "Get financial goals between two dates",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved financial goals between the specified dates"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data"
                    )
            }
    )
    @GetMapping("/betweenDates/{startDate}/{endDate}")
    public ResponseEntity<ResponseModel> getFinancialGoalsBetweenDates(@PathVariable @Valid @NotEmpty LocalDate startDate, @PathVariable @Valid @NotEmpty LocalDate endDate) {
        return financialGoalService.getFinancialGoalsBetweenDates(startDate, endDate);
    }

    @Operation(
            tags = "Get Related Financial Goals",
            description = "Get related financial goals for a user between two dates",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved related financial goals"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data"
                    )
            }
    )
    @GetMapping("/relatedGoals/{userId}/{startDate}/{endDate}")
    public ResponseEntity<ResponseModel> getRelatedFinancialGoals(@PathVariable int userId, @PathVariable @Valid @NotEmpty LocalDate startDate, @PathVariable @Valid @NotEmpty LocalDate endDate) {
        return financialGoalService.getRelatedFinancialGoals(userId, startDate, endDate);
    }

    @Operation(
            tags = "Add Expense to Financial Goal",
            description = "Add an expense to a financial goal",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully added the expense"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data"
                    )
            }
    )
    @PostMapping("/addExpense")
    public ResponseEntity<ResponseModel> addExpense(@Valid @RequestBody GoalAddAmountDto amountDto) {
        return financialGoalService.addExpense(amountDto);
    }
}
