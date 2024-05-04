package com.hashedin.financialgoal.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hashedin.financialgoal.constant.ProjectConstant;
import com.hashedin.financialgoal.utility.ResponseModel;
import com.hashedin.financialgoal.utility.ResponseUtility;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	ResponseUtility responseUtility;
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<ResponseModel> handleResourceNotFoundException(ResourceNotFoundException ex) {
	    return responseUtility.createErrorResponse(ProjectConstant.RESOURCE_NOT_FOUND_MESSAGE);
	}
	
	@ExceptionHandler({ValidationException.class})
	public ResponseEntity<ResponseModel> handleValidationException(ValidationException ex) {
	    return responseUtility.createErrorResponse(ex.getMessage());
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseModel> handleGenericException(Exception ex) {
	    return responseUtility.createErrorResponse(ProjectConstant.ERROR_STATUS);
	}



}
