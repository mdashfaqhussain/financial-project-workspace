package com.hashedin.budgetexpense.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hashedin.budgetexpense.dto.ExpenseDto;
import com.hashedin.budgetexpense.entity.Budget;
import com.hashedin.budgetexpense.entity.BudgetUser;
import com.hashedin.budgetexpense.entity.Category;
import com.hashedin.budgetexpense.entity.Expense;
import com.hashedin.budgetexpense.exception.InsufficientBudgetException;
import com.hashedin.budgetexpense.exception.ResourceNotFoundException;
import com.hashedin.budgetexpense.exception.UserNotFoundException;
import com.hashedin.budgetexpense.repository.IBudgetRepository;
import com.hashedin.budgetexpense.repository.ICategoryRepository;
import com.hashedin.budgetexpense.repository.IExpenseRepository;
import com.hashedin.budgetexpense.repository.IUserRepository;
import com.hashedin.budgetexpense.service.IExpenceService;
import com.hashedin.budgetexpense.utility.ExpenseServiceHelper;
import com.hashedin.budgetexpense.utility.Mapper;
import com.hashedin.budgetexpense.utility.ResponseModel;
import com.hashedin.budgetexpense.utility.ResponseUtility;

import lombok.extern.slf4j.Slf4j;

@Service

public final class ExpenseServiceImpl implements IExpenceService {

	private static final Logger logger = LoggerFactory.getLogger(ExpenseServiceImpl.class);

	@Autowired
	IExpenseRepository expenseRepository;

	@Autowired
	IBudgetRepository budgetRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	ResponseUtility responseUtility;

	@Autowired
	ICategoryRepository categoryRepository;

	@Override
	public ResponseEntity<ResponseModel> createExpense(ExpenseDto expenseDto) {
		try {
			logger.info("Creating expense: {}", expenseDto);

			Expense expense = ExpenseServiceHelper.createExpenseFromDto(expenseDto, userRepository, budgetRepository,
					categoryRepository);

			expenseRepository.save(expense);
			budgetRepository.save(expense.getBudget());

			logger.info("Expense created successfully");

			return responseUtility.createSuccessResponse("Expense created successfully", null);
		} catch (UserNotFoundException e) {
			logger.error("Error creating expense: User not found", e);
			return responseUtility.createErrorResponse("User not found");
		} catch (ResourceNotFoundException e) {
			logger.error("Error creating expense: Resource not found", e);
			return responseUtility.createErrorResponse("Resource not found");
		} catch (InsufficientBudgetException e) {
			logger.error("Error creating expense: Insufficient budget", e);
			return responseUtility.createErrorResponse("Insufficient budget");
		} catch (Exception e) {
			logger.error("Error creating expense", e);
			return responseUtility.createErrorResponse("Error saving the expense");
		}
	}

	@Override
	public ResponseEntity<ResponseModel> updateExpense(int expenseId, ExpenseDto expenseDto) {
	    try {
	        logger.info("Updating expense with ID {}: {}", expenseId, expenseDto);

	        // Retrieve existing expense
	        Expense existingExpense = expenseRepository.findById(expenseId)
	                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with ID: " + expenseId));

	        // Retrieve user
	        BudgetUser user = userRepository.findById(expenseDto.getUserId())
	                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + expenseDto.getUserId()));

	        // Retrieve budget
	        Budget budget = budgetRepository.findById(expenseDto.getBudgetId())
	                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + expenseDto.getBudgetId()));

	        // Retrieve category
	        Category category = categoryRepository.findById(expenseDto.getCategoryId())
	                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + expenseDto.getCategoryId()));

	        // Update existing expense
	        existingExpense.setExpenseAmount(expenseDto.getExpenseAmount());
	        existingExpense.setUser(user);
	        existingExpense.setBudget(budget);
	        existingExpense.setCategory(category);

	        // Update budget amount
	        double expenseAmount = expenseDto.getExpenseAmount();
	        double currentAmountLeft = budget.getBudgetAmountLeft();
	        double newAmountLeft = currentAmountLeft - expenseAmount;
	        budget.setBudgetAmountLeft(newAmountLeft);
	        if (newAmountLeft == 0) {
	            budget.setActive(false);
	        }

	        // Save updated entities
	        expenseRepository.save(existingExpense);
	        budgetRepository.save(budget);

	        logger.info("Expense with ID {} updated successfully", expenseId);

	        return responseUtility.createSuccessResponse("Expense updated successfully", null);
	    } catch (UserNotFoundException | ResourceNotFoundException e) {
	        logger.error("Error updating expense: {}", e.getMessage(), e);
	        return responseUtility.createErrorResponse(e.getMessage());
	    } catch (Exception e) {
	        logger.error("Error updating expense", e);
	        return responseUtility.createErrorResponse("Error updating the expense");
	    }
	}


	@Override
	public ResponseEntity<ResponseModel> deleteExpense(int expenseId) {
	    try {
	        logger.info("Deleting expense with ID: {}", expenseId);

	        // Retrieve existing expense
	        Expense existingExpense = expenseRepository.findById(expenseId)
	                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with ID: " + expenseId));

	        // Retrieve budget associated with the expense
	        Budget budget = existingExpense.getBudget();

	        // Update budget amount by adding back the expense amount
	        double expenseAmount = existingExpense.getExpenseAmount();
	        double currentAmountLeft = budget.getBudgetAmountLeft();
	        double newAmountLeft = currentAmountLeft + expenseAmount;
	        budget.setBudgetAmountLeft(newAmountLeft);
	        if (newAmountLeft > 0) {
	            budget.setActive(true);
	        }

	        // Delete the expense
	        expenseRepository.delete(existingExpense);

	        // Save the updated budget
	        budgetRepository.save(budget);

	        logger.info("Expense with ID {} deleted successfully", expenseId);

	        return responseUtility.createSuccessResponse("Expense deleted successfully", null);
	    } catch (ResourceNotFoundException e) {
	        logger.error("Error deleting expense: {}", e.getMessage(), e);
	        return responseUtility.createErrorResponse(e.getMessage());
	    } catch (Exception e) {
	        logger.error("Error deleting expense", e);
	        return responseUtility.createErrorResponse("Error deleting the expense");
	    }
	}


	
	@Override
	public ResponseEntity<ResponseModel> getExpense(int expenseId) {
	    try {
	        logger.info("Retrieving expense with ID: {}", expenseId);

	        // Retrieve expense by ID
	        Expense expense = expenseRepository.findById(expenseId)
	                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with ID: " + expenseId));

	        logger.info("Expense with ID {} retrieved successfully", expenseId);

	        // Map expense entity to DTO
	        ExpenseDto expenseDto = Mapper.mapEntityToDto(expense);

	        // Create success response
	        return responseUtility.createSuccessResponse("Expense retrieved successfully", expenseDto);
	    } catch (ResourceNotFoundException e) {
	        logger.error("Error retrieving expense: {}", e.getMessage(), e);
	        return responseUtility.createErrorResponse(e.getMessage());
	    } catch (Exception e) {
	        logger.error("Error retrieving expense", e);
	        return responseUtility.createErrorResponse("Error retrieving the expense");
	    }
	}


	
	@Override
	public ResponseEntity<ResponseModel> getAllExpenses(int userId) {
	    try {
	        // Retrieve the user from the user repository
	        Optional<BudgetUser> userOptional = userRepository.findById(userId);
	        if (userOptional.isEmpty()) {
	            throw new UserNotFoundException("User not found with ID: " + userId);
	        }
	        BudgetUser user = userOptional.get();

	        // Retrieve all expenses associated with the user
	        List<Expense> expenses = user.getExpenses();

	        // Map the expenses to DTOs
	        List<ExpenseDto> expenseDtos = expenses.stream()
	                .map(Mapper::mapEntityToDto)
	                .collect(Collectors.toList());

	        // Create a success response with the list of DTOs
	        return responseUtility.createSuccessResponse("Expenses retrieved successfully", expenseDtos);
	    } catch (UserNotFoundException e) {
	        logger.error("Error fetching expenses: User not found with ID: {}", userId, e);
	        return responseUtility.createErrorResponse("User not found");
	    } catch (Exception e) {
	        logger.error("Error fetching expenses for user with ID: {}", userId, e);
	        return responseUtility.createErrorResponse("Error fetching expenses");
	    }
	}


	
	@Override
	public ResponseEntity<ResponseModel> getBudgetExpenses(int budgetId) {
	    try {
	        // Retrieve the budget from the budget repository
	        Optional<Budget> budgetOptional = budgetRepository.findById(budgetId);
	        if (budgetOptional.isEmpty()) {
	            throw new ResourceNotFoundException("Budget not found with ID: " + budgetId);
	        }
	        Budget budget = budgetOptional.get();

	        // Retrieve all expenses associated with the budget
	        List<Expense> expenses = budget.getExpenses();

	        // Map the expenses to DTOs
	        List<ExpenseDto> expenseDtos = expenses.stream()
	                .map(Mapper::mapEntityToDto)
	                .collect(Collectors.toList());

	        // Create a success response with the list of DTOs
	        return responseUtility.createSuccessResponse("Expenses for budget retrieved successfully", expenseDtos);
	    } catch (ResourceNotFoundException e) {
	        logger.error("Error fetching expenses for budget: Budget not found with ID: {}", budgetId, e);
	        return responseUtility.createErrorResponse("Budget not found");
	    } catch (Exception e) {
	        logger.error("Error fetching expenses for budget with ID: {}", budgetId, e);
	        return responseUtility.createErrorResponse("Error fetching expenses for budget");
	    }
	}


}
