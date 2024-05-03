package com.hasdedin.user.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hasdedin.user.dto.ResponseModel;

public class ResponseUtility {
	
	
	ResponseModel responseModel = new ResponseModel();
	
	public ResponseEntity<ResponseModel> createResponse(ResponseModel model){
		responseModel.setStatuscode(model.getStatuscode());
		responseModel.setStatus(model.getStatus());
		responseModel.setMessage(model.getMessage());
		responseModel.setData(model.getData());
		
		if(model.getStatus().equalsIgnoreCase("SUCCESS")) {
			return ResponseEntity.status(HttpStatus.OK).body(responseModel);
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
		}
		
	}

}
