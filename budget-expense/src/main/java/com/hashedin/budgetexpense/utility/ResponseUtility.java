package com.hashedin.budgetexpense.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hashedin.budgetexpense.constant.ProjectConstants;


public class ResponseUtility {
	
	
	ResponseModel responseModel = new ResponseModel();
	
	public ResponseEntity<ResponseModel> createSuccessResponse(String message, Object data) {
        ResponseModel model = new ResponseModel();
        model.setMessage(message);
        model.setStatus(ProjectConstants.SUCCESS_STATUS);
        model.setStatuscode(ProjectConstants.SUCCESS_CODE);
        model.setData(data);
        return ResponseEntity.status(HttpStatus.OK).body(model);
    }

	
	 public ResponseEntity<ResponseModel> createErrorResponse(String message) {
	        ResponseModel model = new ResponseModel();
	        model.setMessage(message);
	        model.setStatus(ProjectConstants.ERROR_STATUS);
	        model.setStatuscode(ProjectConstants.ERROR_CODE);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(model);
	    }
}
