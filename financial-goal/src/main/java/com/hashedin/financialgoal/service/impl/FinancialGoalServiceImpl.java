package com.hashedin.financialgoal.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hashedin.financialgoal.constant.ProjectConstant;
import com.hashedin.financialgoal.dto.FinancialGoalDto;
import com.hashedin.financialgoal.dto.GoalAddAmountDto;
import com.hashedin.financialgoal.entity.BudgetUser;
import com.hashedin.financialgoal.entity.FinancialGoal;
import com.hashedin.financialgoal.exception.ResourceNotFoundException;
import com.hashedin.financialgoal.exception.ValidationException;
import com.hashedin.financialgoal.repository.IFinancialGoalRepository;
import com.hashedin.financialgoal.repository.IUserRepository;
import com.hashedin.financialgoal.service.IFinancialGoalService;
import com.hashedin.financialgoal.utility.Mapper;
import com.hashedin.financialgoal.utility.ResponseModel;
import com.hashedin.financialgoal.utility.ResponseUtility;

import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FinancialGoalServiceImpl implements IFinancialGoalService {

	@Autowired
	private IFinancialGoalRepository repository;

	@Autowired
	private ResponseUtility responseUtility;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public ResponseEntity<ResponseModel> saveGoal(FinancialGoalDto goalDto) {
	    try {
	        if (goalDto.getEnteredAmount() > goalDto.getTotalAmount()) {
	            throw new ValidationException("Current amount must be less than or equal to the total amount");
	        }

	        // Retrieve user from the database
	        Optional<BudgetUser> existingUserOptional = userRepository.findById(goalDto.getUserId());
	        BudgetUser user = existingUserOptional.orElseThrow(() -> new ResourceNotFoundException("User not found"));

	        // Convert DTO to entity
	        FinancialGoal financialGoal = Mapper.convertToEntity(goalDto);

	        // Associate the user with the financial goal
	        financialGoal.setUser(user);

	        // Save the financial goal
	        repository.save(financialGoal);

	        return responseUtility.createSuccessResponse(ProjectConstant.CREATE_SUCCESS_MESSAGE, null);
	    } catch (ValidationException | ResourceNotFoundException e) {
	        log.error("Exception occurred while saving financial goal: {}", e.getMessage());
	        return responseUtility.createErrorResponse(e.getMessage());
	    } catch (Exception e) {
	        log.error("Error occurred while saving financial goal: {}", e.getMessage());
	        return responseUtility.createErrorResponse(ProjectConstant.CREATE_ERROR_MESSAGE);
	    }
	}


	@Override
	public ResponseEntity<ResponseModel> addExpense(GoalAddAmountDto amountDto) {
		try {
			FinancialGoal existingGoal = repository.findById(amountDto.getGoalId())
					.orElseThrow(() -> new ResourceNotFoundException("Financial goal not found"));

			double currentEnteredAmount = existingGoal.getEnteredAmount();
			double totalAmountFromDB = existingGoal.getTotalAmount();
			double newEnteredAmount = currentEnteredAmount + amountDto.getExpenseAmount();

			if (newEnteredAmount <= totalAmountFromDB) {

				existingGoal.setEnteredAmount(newEnteredAmount);
				repository.save(existingGoal);

				return responseUtility.createSuccessResponse(ProjectConstant.CREATE_SUCCESS_MESSAGE, null);
			} else {
				throw new ValidationException("Adding expense exceeds the total amount");
			}
		} catch (ResourceNotFoundException e) {
			log.error("Resource not found exception occurred: {}", e.getMessage());

			return responseUtility.createErrorResponse("Financial goal not found");
		} catch (ValidationException e) {
			log.error("Validation exception occurred: {}", e.getMessage());

			return responseUtility.createErrorResponse(e.getMessage());
		} catch (Exception e) {
			log.error("Error occurred while adding expense: {}", e.getMessage());

			return responseUtility.createErrorResponse("Error adding expense");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> deleteFinancialGoal(int goalId) {
		try {
			repository.deleteById(goalId);
			return responseUtility.createSuccessResponse(ProjectConstant.DELETE_SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			log.error("Error occurred while deleting financial goal: {}", e.getMessage());
			return responseUtility.createErrorResponse(ProjectConstant.DELETE_ERROR_MESSAGE);
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getAllFinancialGoals() {
		try {
			List<FinancialGoal> financialGoals = repository.findAll();
			return responseUtility.createSuccessResponse(ProjectConstant.READ_SUCCESS_MESSAGE, financialGoals);
		} catch (Exception e) {
			log.error("Error occurred while fetching all financial goals: {}", e.getMessage());

			return responseUtility.createErrorResponse(ProjectConstant.READ_ERROR_MESSAGE);
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

			return responseUtility.createSuccessResponse(ProjectConstant.UPDATE_SUCCESS_MESSAGE, null);
		} catch (NotFoundException e) {
			log.error("Resource not found exception occurred: {}", e.getMessage());

			return responseUtility.createErrorResponse("Financial goal not found");
		} catch (Exception e) {
			log.error("Error occurred while updating financial goal: {}", e.getMessage());

			return responseUtility.createErrorResponse(ProjectConstant.UPDATE_ERROR_MESSAGE);
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getFinancialGoalsByStartDate(LocalDate startDate) {
		try {
			List<FinancialGoal> financialGoals = repository.findByStartDate(startDate);
			return responseUtility.createSuccessResponse("Financial goals retrieved successfully", financialGoals);
		} catch (Exception e) {
			return responseUtility.createErrorResponse("Error retrieving financial goals by start date");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getFinancialGoalsByEndDate(LocalDate endDate) {
		try {
			List<FinancialGoal> financialGoals = repository.findByEndDate(endDate);
			return responseUtility.createSuccessResponse("Financial goals retrieved successfully", financialGoals);
		} catch (Exception e) {
			return responseUtility.createErrorResponse("Error retrieving financial goals by end date");
		}

	}

	@Override
	public ResponseEntity<ResponseModel> getFinancialGoalsBetweenDates(LocalDate startDate, LocalDate endDate) {
		try {
			List<FinancialGoal> financialGoals = repository.findByStartDateBetween(startDate, endDate);
			return responseUtility.createSuccessResponse("Financial goals retrieved successfully", financialGoals);
		} catch (Exception e) {
			return responseUtility.createErrorResponse("Error retrieving financial goals between dates");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getRelatedFinancialGoals(int userId, LocalDate startDate, LocalDate endDate) {

		try {
			List<FinancialGoal> financialGoals = repository.findByUserId(userId);
			return responseUtility.createSuccessResponse("Financial goals retrieved successfully", financialGoals);
		} catch (Exception e) {
			return responseUtility.createErrorResponse("Error retrieving financial goals by user ID");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getFinancialGoalsByUserId(int userId) {
		try {
			log.info("Querying financial goals for user ID: {}", userId);
			List<FinancialGoal> financialGoals = repository.findByUserId(userId);
			log.info("Found {} financial goals for user ID: {}", financialGoals.size(), userId);
			return responseUtility.createSuccessResponse(ProjectConstant.SUCCESS_STATUS, financialGoals);
		} catch (Exception e) {
			log.error("Error retrieving financial goals by user ID: {}", userId, e);
			return responseUtility.createErrorResponse("Error retrieving financial goals by user ID");
		}
	}

}
