package com.hashedin.financialgoal.service;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.http.ResponseEntity;

import com.hashedin.financialgoal.dto.FinancialGoalDto;
import com.hashedin.financialgoal.dto.GoalAddAmountDto;
import com.hashedin.financialgoal.utility.ResponseModel;

public interface IFinancialGoalService {

	ResponseEntity<ResponseModel> saveGoal(FinancialGoalDto goalDto);

	ResponseEntity<ResponseModel> deleteFinancialGoal(int goalId);

	ResponseEntity<ResponseModel> getAllFinancialGoals();

	ResponseEntity<ResponseModel> updateFinancialGoal(int goalId, FinancialGoalDto financialGoalDto);

	

	ResponseEntity<ResponseModel> getFinancialGoalsByUserId(int userId);

	ResponseEntity<ResponseModel> getFinancialGoalsByStartDate(LocalDate startDate);

	ResponseEntity<ResponseModel> getFinancialGoalsByEndDate(LocalDate endDate);

	ResponseEntity<ResponseModel> getFinancialGoalsBetweenDates(LocalDate startDate, LocalDate endDate);

	ResponseEntity<ResponseModel> getRelatedFinancialGoals(int userId, LocalDate startDate, LocalDate endDate);

	ResponseEntity<ResponseModel> addExpense(GoalAddAmountDto amountDto);

}
