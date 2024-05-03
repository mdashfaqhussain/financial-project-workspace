package com.hashedin.financialgoal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.hashedin.financialgoal.constant.ProjectConstant;
import com.hashedin.financialgoal.dto.FinancialGoalDto;
import com.hashedin.financialgoal.entity.FinancialGoal;
import com.hashedin.financialgoal.exception.ResourceNotFoundException;
import com.hashedin.financialgoal.repository.IFinancialGoalRepository;
import com.hashedin.financialgoal.service.IFinancialGoalService;
import com.hashedin.financialgoal.utility.Mapper;
import com.hashedin.financialgoal.utility.ResponseModel;
import com.hashedin.financialgoal.utility.ResponseUtility;

import jakarta.ws.rs.NotFoundException;

public class FinancialGoalServiceImpl implements IFinancialGoalService {

	@Autowired
	private IFinancialGoalRepository repository;

	@Autowired
	private ResponseUtility responseUtility;

	@Override
	public ResponseEntity<ResponseModel> saveGoal(FinancialGoalDto goalDto) {
		try {
			FinancialGoal financialGoal = Mapper.convertToEntity(goalDto);
			financialGoal.setUserId(2);
			repository.save(financialGoal);
			ResponseModel model = new ResponseModel();
			model.setMessage(ProjectConstant.CREATE_SUCCESS_MESSAGE);
			model.setStatus(ProjectConstant.SUCCESS_STATUS);
			model.setStatuscode(ProjectConstant.CREATED_CODE);
			return responseUtility.createResponse(model);
		} catch (Exception e) {
			ResponseModel errorModel = new ResponseModel();
			errorModel.setMessage(ProjectConstant.CREATE_ERROR_MESSAGE);
			errorModel.setStatus(ProjectConstant.ERROR_STATUS);
			errorModel.setStatuscode(ProjectConstant.ERROR_CODE);
			return responseUtility.createResponse(errorModel);
		}
	}

	@Override
	public ResponseEntity<ResponseModel> deleteFinancialGoal(int goalId) {
		try {
			repository.deleteById(goalId);
			ResponseModel model = new ResponseModel();
			model.setMessage(ProjectConstant.DELETE_SUCCESS_MESSAGE);
			model.setStatus(ProjectConstant.SUCCESS_STATUS);
			model.setStatuscode(ProjectConstant.SUCCESS_CODE);
			return responseUtility.createResponse(model);
		} catch (Exception e) {
			ResponseModel errorModel = new ResponseModel();
			errorModel.setMessage(ProjectConstant.DELETE_ERROR_MESSAGE);
			errorModel.setStatus(ProjectConstant.ERROR_STATUS);
			errorModel.setStatuscode(ProjectConstant.ERROR_CODE);
			return responseUtility.createResponse(errorModel);
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getAllFinancialGoals() {
		try {
			List<FinancialGoal> financialGoals = repository.findAll();
			ResponseModel model = new ResponseModel();
			model.setData(financialGoals);
			model.setMessage(ProjectConstant.READ_SUCCESS_MESSAGE);
			model.setStatus(ProjectConstant.SUCCESS_STATUS);
			model.setStatuscode(ProjectConstant.SUCCESS_CODE);
			return responseUtility.createResponse(model);
		} catch (Exception e) {
			ResponseModel errorModel = new ResponseModel();
			errorModel.setMessage(ProjectConstant.READ_ERROR_MESSAGE);
			errorModel.setStatus(ProjectConstant.ERROR_STATUS);
			errorModel.setStatuscode(ProjectConstant.ERROR_CODE);
			return responseUtility.createResponse(errorModel);
		}
	}

	@Override
	public ResponseEntity<ResponseModel> updateFinancialGoal(int goalId, FinancialGoalDto financialGoalDto) {
		try {
			FinancialGoal existingGoal = repository.findById(goalId)
					.orElseThrow(() -> new ResourceNotFoundException("Financial goal not found"));

			existingGoal.setGoalName(financialGoalDto.getGoalName());
			existingGoal.setTotalAmount(financialGoalDto.getTotalAmount());
			existingGoal.setEnteredAmount(financialGoalDto.getEnteredAmount());
			existingGoal.setStartDate(financialGoalDto.getStartDate());
			existingGoal.setEndDate(financialGoalDto.getEndDate());
			

			repository.save(existingGoal);

			ResponseModel model = new ResponseModel();
			model.setMessage(ProjectConstant.UPDATE_SUCCESS_MESSAGE);
			model.setStatus(ProjectConstant.SUCCESS_STATUS);
			model.setStatuscode(ProjectConstant.SUCCESS_CODE);
			return responseUtility.createResponse(model);
		} catch (NotFoundException e) {
			ResponseModel errorModel = new ResponseModel();
			errorModel.setMessage("Financial goal not found");
			errorModel.setStatus(ProjectConstant.ERROR_STATUS);
			errorModel.setStatuscode(ProjectConstant.NOT_FOUND_CODE);
			return responseUtility.createResponse(errorModel);
		} catch (Exception e) {
			ResponseModel errorModel = new ResponseModel();
			errorModel.setMessage(ProjectConstant.UPDATE_ERROR_MESSAGE);
			errorModel.setStatus(ProjectConstant.ERROR_STATUS);
			errorModel.setStatuscode(ProjectConstant.ERROR_CODE);
			return responseUtility.createResponse(errorModel);
		}
	}

}