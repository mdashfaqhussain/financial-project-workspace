package com.hashedin.budgetexpense.service;

import org.springframework.http.ResponseEntity;

import com.hashedin.budgetexpense.dto.BudgetDto;
import com.hashedin.budgetexpense.utility.ResponseModel;

public interface IBudgetService {

	ResponseEntity<ResponseModel> createBudget(BudgetDto budgetDto);

	ResponseEntity<ResponseModel> deleteBudget(int budgetId);

	ResponseEntity<ResponseModel> getBudget(int budgetId, int userId);
	
	ResponseEntity<ResponseModel> updateBudget(int budgetId, BudgetDto budgetDto);

	ResponseEntity<ResponseModel> getAllBudgetsByUserId(int userId);
	
    ResponseEntity<ResponseModel> getBudgetsByStartDate( int userId,  String startDate);
    
    ResponseEntity<ResponseModel> getBudgetsByEndDate( int userId,  String endDate);

    ResponseEntity<ResponseModel> getBudgetsByDateRange( int userId,  String startDate,  String endDate);
    
    ResponseEntity<ResponseModel> getActiveBudgets(int userId, boolean isActive);



}
