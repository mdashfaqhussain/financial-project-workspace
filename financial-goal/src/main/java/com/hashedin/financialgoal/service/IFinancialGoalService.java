package com.hashedin.financialgoal.service;

import org.springframework.http.ResponseEntity;

import com.hashedin.financialgoal.dto.FinancialGoalDto;
import com.hashedin.financialgoal.utility.ResponseModel;

public interface IFinancialGoalService {

	ResponseEntity<ResponseModel> saveGoal(FinancialGoalDto goalDto);

	ResponseEntity<ResponseModel> deleteFinancialGoal(int goalId);

	ResponseEntity<ResponseModel> getAllFinancialGoals();

	ResponseEntity<ResponseModel> updateFinancialGoal(int goalId, FinancialGoalDto financialGoalDto);

}