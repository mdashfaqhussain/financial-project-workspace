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
	    ResponseModel responseModel = new ResponseModel();
	    responseModel.setMessage(ProjectConstant.RESOURCE_NOT_FOUND_MESSAGE);
	    responseModel.setStatuscode(ProjectConstant.NOT_FOUND_CODE);
	    responseModel.setStatus(ProjectConstant.ERROR_STATUS);
	    return responseUtility.createResponse(responseModel);
	}


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel> handleGenericException(Exception ex) {
    	ResponseModel responseModel = new ResponseModel();
    	responseModel.setStatus(ProjectConstant.ERROR_STATUS);
    	responseModel.setStatuscode(ProjectConstant.ERROR_CODE);
        return responseUtility.createResponse(responseModel);
    }


}
