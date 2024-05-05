package com.hashedin.budgetexpense.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hashedin.budgetexpense.constant.ProjectConstants;
import com.hashedin.budgetexpense.utility.ResponseModel;
import com.hashedin.budgetexpense.utility.ResponseUtility;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ResponseUtility responseUtility;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseModel> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return responseUtility.createErrorResponse(ProjectConstants.CATEGORY_FOUND_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseModel> handleGenericException(Exception ex) {
		return responseUtility.createErrorResponse(ProjectConstants.ERROR_STATUS);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseModel> handleUserNotFoundException(UserNotFoundException ex) {
		return responseUtility.createErrorResponse(ProjectConstants.USER_NOT_FOUND);
	}
	
	@ExceptionHandler(InsufficientBudgetException.class)
	public ResponseEntity<ResponseModel> handleInsufficientBudgetException(InsufficientBudgetException ex) {
		return responseUtility.createErrorResponse(ProjectConstants.INSUFFICIENT_BUDGET);
	}

}
