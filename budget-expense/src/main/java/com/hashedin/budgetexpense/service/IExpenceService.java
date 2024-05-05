package com.hashedin.budgetexpense.service;

import org.springframework.http.ResponseEntity;

import com.hashedin.budgetexpense.dto.ExpenseDto;
import com.hashedin.budgetexpense.utility.ResponseModel;

public interface IExpenceService {
	
	ResponseEntity<ResponseModel> createExpense(ExpenseDto expenseDto);
	
	ResponseEntity<ResponseModel> updateExpense(int expenseId, ExpenseDto expenseDto);
	
	ResponseEntity<ResponseModel> deleteExpense(int expenseId);
	
	ResponseEntity<ResponseModel> getExpense(int expenseId);
	
	ResponseEntity<ResponseModel> getAllExpenses(int userId);
	
	ResponseEntity<ResponseModel> getBudgetExpenses(int budgetId);
	
}
