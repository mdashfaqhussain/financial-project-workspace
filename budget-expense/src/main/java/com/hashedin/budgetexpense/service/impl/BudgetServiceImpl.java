package com.hashedin.budgetexpense.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hashedin.budgetexpense.constant.ProjectConstants;
import com.hashedin.budgetexpense.dto.BudgetDto;
import com.hashedin.budgetexpense.entity.Budget;
import com.hashedin.budgetexpense.entity.BudgetUser;
import com.hashedin.budgetexpense.exception.ResourceNotFoundException;
import com.hashedin.budgetexpense.exception.UserNotFoundException;
import com.hashedin.budgetexpense.repository.IBudgetRepository;
import com.hashedin.budgetexpense.repository.IUserRepository;
import com.hashedin.budgetexpense.service.IBudgetService;
import com.hashedin.budgetexpense.utility.Mapper;
import com.hashedin.budgetexpense.utility.ResponseModel;
import com.hashedin.budgetexpense.utility.ResponseUtility;
import com.netflix.discovery.provider.Serializer;

import ch.qos.logback.classic.Logger;

@Service
public final class BudgetServiceImpl implements IBudgetService {

	private final Logger logger = (Logger) LoggerFactory.getLogger(BudgetServiceImpl.class);

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IBudgetRepository budgetRepository;

	@Autowired
	ResponseUtility responseUtility;

	@Override
	public ResponseEntity<ResponseModel> createBudget(BudgetDto budgetDto) {
		try {
			logger.info("Creating budget :{}", budgetDto);

			BudgetUser user = userRepository.findById(budgetDto.getUserId()).orElseThrow(
					() -> new UserNotFoundException("User with ID " + budgetDto.getUserId() + " not found"));

			Budget budget = Mapper.createBudgetFromDto(budgetDto, user);
			budgetRepository.save(budget);
			logger.info("Budget created successfully: {}", budgetDto);
			return responseUtility.createSuccessResponse("Budget Created Successfully", null);
		} catch (Exception ex) {
			logger.error("Failed to create budget. User not found.", ex);
			return responseUtility.createErrorResponse("Error creating budget");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> deleteBudget(int budgetId) {
		try {
			logger.info("Deleting budget with ID: {}", budgetId);

			if (!budgetRepository.existsById(budgetId)) {
				logger.error("Budget with ID {} not found.", budgetId);
				return responseUtility.createErrorResponse("Budget not found");
			}

			budgetRepository.deleteById(budgetId);

			logger.info("Budget with ID {} deleted successfully.", budgetId);
			return responseUtility.createSuccessResponse("Budget deleted successfully", null);
		} catch (Exception ex) {
			logger.error("Failed to delete budget with ID {}.", budgetId, ex);
			return responseUtility.createErrorResponse("Failed to delete budget");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> updateBudget(int budgetId, BudgetDto budgetDto) {
		try {
			logger.info("Updating budget with ID: {}", budgetId);

			Budget existingBudget = budgetRepository.findById(budgetId)
					.orElseThrow(() -> new RuntimeException("Budget with ID " + budgetId + " not found"));

			Mapper.mapBudgetDtoToBudget(existingBudget, budgetDto);

			budgetRepository.save(existingBudget);

			logger.info("Budget with ID {} updated successfully.", budgetId);
			return responseUtility.createSuccessResponse("Budget updated successfully", null);
		} catch (Exception ex) {
			logger.error("Failed to update budget with ID {}.", budgetId, ex);
			return responseUtility.createErrorResponse("Failed to update budget");
		}

	}

	@Override
	public ResponseEntity<ResponseModel> getAllBudgetsByUserId(int userId) {
		try {
			logger.info("Fetching all budgets for user with ID: {}", userId);
			Optional<BudgetUser> userOptional = userRepository.findById(userId);
			if (!userOptional.isPresent()) {
				logger.error("User with ID {} not found.", userId);
				return responseUtility.createErrorResponse("User not found");
			}
			BudgetUser user = userOptional.get();
			List<Budget> budgets = budgetRepository.findByUser(user);
			List<BudgetDto> budgetDtos = budgets.stream().map(Mapper::mapBudgetToDto).collect(Collectors.toList());
			logger.info("Fetched {} budgets for user with ID: {}", budgetDtos.size(), userId);
			return responseUtility.createSuccessResponse("Budgets fetched successfully", budgetDtos);
		} catch (Exception ex) {
			logger.error("Failed to fetch budgets for user with ID {}.", userId, ex);
			return responseUtility.createErrorResponse("Failed to fetch budgets");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getBudgetsByStartDate(int userId, String startDate) {
		try {
			logger.info("Retrieving budgets for user with ID {} starting from: {}", userId, startDate);

			LocalDate parsedStartDate = LocalDate.parse(startDate);

			BudgetUser user = userRepository.findById(userId).orElseThrow(
					() -> new UserNotFoundException(ProjectConstants.USER_NOT_FOUND + " with userid " + userId));

			List<Budget> budgets = budgetRepository.findByUserAndStartDate(user, parsedStartDate);

			List<BudgetDto> budgetDtos = budgets.stream().map(Mapper::mapBudgetToDto).collect(Collectors.toList());

			logger.info("Retrieved {} budgets.", budgetDtos.size());
			return responseUtility.createSuccessResponse("Budgets retrieved successfully", budgetDtos);
		} catch (Exception ex) {
			logger.error("Failed to retrieve budgets by start date.", ex);
			return responseUtility.createErrorResponse("Failed to retrieve budgets by start date");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getBudgetsByEndDate(int userId, String endDate) {
		try {
			logger.info("Retrieving budgets for user with ID {} ending on: {}", userId, endDate);

			LocalDate parsedEndDate = LocalDate.parse(endDate);

			BudgetUser user = userRepository.findById(userId)
					.orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

			List<Budget> budgets = budgetRepository.findByUserAndEndDate(user, parsedEndDate);

			List<BudgetDto> budgetDtos = budgets.stream().map(Mapper::mapBudgetToDto).collect(Collectors.toList());

			logger.info("Retrieved {} budgets.", budgetDtos.size());
			return responseUtility.createSuccessResponse("Budgets retrieved successfully", budgetDtos);
		} catch (Exception ex) {
			logger.error("Failed to retrieve budgets by end date.", ex);
			return responseUtility.createErrorResponse("Failed to retrieve budgets by end date");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getBudgetsByDateRange(int userId, String startDate, String endDate) {
		try {
			logger.info("Retrieving budgets for user with ID {} in the date range: {} to {}", userId, startDate,
					endDate);

			LocalDate parsedStartDate = LocalDate.parse(startDate);
			LocalDate parsedEndDate = LocalDate.parse(endDate);

			BudgetUser user = userRepository.findById(userId)
					.orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

			List<Budget> budgets = budgetRepository.findByUserAndStartDateBetween(user,parsedStartDate,parsedEndDate);

			List<BudgetDto> budgetDtos = budgets.stream().map(Mapper::mapBudgetToDto).collect(Collectors.toList());

			logger.info("Retrieved {} budgets.", budgetDtos.size());
			return responseUtility.createSuccessResponse("Budgets retrieved successfully", budgetDtos);
		} catch (Exception ex) {
			logger.error("Failed to retrieve budgets by date range.", ex);
			return responseUtility.createErrorResponse("Failed to retrieve budgets by date range");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getBudget(int budgetId, int userId) {
		try {
			logger.info("Retrieving budget with ID {} for user with ID {}", budgetId, userId);

			BudgetUser user = userRepository.findById(userId)
					.orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

			Budget budget = budgetRepository.findByBudgetIdAndUser(budgetId, user)
					.orElseThrow(() -> new ResourceNotFoundException(
							"Budget with ID " + budgetId + " not found for user with ID " + userId));

			BudgetDto budgetDto = Mapper.mapBudgetToDto(budget);

			logger.info("Budget with ID {} retrieved successfully.", budgetId);
			return responseUtility.createSuccessResponse("Budget retrieved successfully", budgetDto);
		} catch (Exception ex) {
			logger.error("Failed to retrieve budget with ID {} for user with ID {}.", budgetId, userId, ex);
			return responseUtility.createErrorResponse("Failed to retrieve budget");
		}
	}

	@Override
    public ResponseEntity<ResponseModel> getActiveBudgets(int userId, boolean isActive) {
        try {
            Optional<BudgetUser> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                throw new UserNotFoundException("User not found with ID: " + userId);
            }
            BudgetUser user = userOptional.get();
            
            List<Budget> activeBudgets = budgetRepository.findByUserAndIsActive(user, isActive);
            List<BudgetDto> activeBudgetDtos = activeBudgets.stream()
                    .map(Mapper::mapBudgetToDto)
                    .collect(Collectors.toList());
            return responseUtility.createSuccessResponse("Active budgets retrieved successfully", activeBudgetDtos);
        } catch (UserNotFoundException e) {
            logger.error("Error fetching active budgets: {}", e.getMessage(), e);
            return responseUtility.createErrorResponse(e.getMessage());
        } catch (Exception e) {
            logger.error("Error fetching active budgets for user with ID: {}", userId, e);
            return responseUtility.createErrorResponse("Error fetching active budgets");
        }
    }

}
