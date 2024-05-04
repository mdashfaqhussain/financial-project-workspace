package com.hashedin.financialgoal.constant;

public class ProjectConstant {

	public static final int SUCCESS_CODE = 200;
	public static final int CREATED_CODE = 201;
	public static final int NOT_FOUND_CODE = 404;
	public static final int ERROR_CODE = 500;

	public static final String SUCCESS_STATUS = "Success";
	public static final String ERROR_STATUS = "Error";

	public static final String DEFAULT_SUCCESS_MESSAGE = "Operation completed successfully";
	public static final String DEFAULT_ERROR_MESSAGE = "An error occurred while processing the request";

	public static final String CREATE_SUCCESS_MESSAGE = "Record created successfully";
	public static final String CREATE_ERROR_MESSAGE = "Failed to create record";

	public static final String READ_SUCCESS_MESSAGE = "Record retrieved successfully";
	public static final String READ_ERROR_MESSAGE = "Failed to retrieve record";

	public static final String UPDATE_SUCCESS_MESSAGE = "Record updated successfully";
	public static final String UPDATE_ERROR_MESSAGE = "Failed to update record";

	public static final String DELETE_SUCCESS_MESSAGE = "Record deleted successfully";
	public static final String DELETE_ERROR_MESSAGE = "Failed to delete record";
	
	public static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource not found";
	
	
	public static final String VALIDATION_EXCEPTION_MESSAGE="Entered the amount less than total amount";
}
