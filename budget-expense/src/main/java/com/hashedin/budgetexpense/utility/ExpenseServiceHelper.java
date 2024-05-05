package com.hashedin.budgetexpense.utility;

import com.hashedin.budgetexpense.dto.ExpenseDto;
import com.hashedin.budgetexpense.entity.Budget;
import com.hashedin.budgetexpense.entity.BudgetUser;
import com.hashedin.budgetexpense.entity.Category;
import com.hashedin.budgetexpense.entity.Expense;
import com.hashedin.budgetexpense.exception.ResourceNotFoundException;
import com.hashedin.budgetexpense.exception.UserNotFoundException;
import com.hashedin.budgetexpense.repository.IBudgetRepository;
import com.hashedin.budgetexpense.repository.ICategoryRepository;
import com.hashedin.budgetexpense.repository.IUserRepository;

public class ExpenseServiceHelper {

    public static Expense createExpenseFromDto(ExpenseDto expenseDto, IUserRepository userRepository,
                                               IBudgetRepository budgetRepository, ICategoryRepository categoryRepository) {
        BudgetUser user = userRepository.findById(expenseDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + expenseDto.getUserId()));

        Budget budget = budgetRepository.findById(expenseDto.getBudgetId())
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + expenseDto.getBudgetId()));

        Category category = categoryRepository.findById(expenseDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + expenseDto.getCategoryId()));

        Expense expense = Mapper.mapDtoToEntity(expenseDto);
        expense.setUser(user);
        expense.setBudget(budget);
        expense.setCategory(category);

        double expenseAmount = expenseDto.getExpenseAmount();
        double currentAmountLeft = budget.getBudgetAmountLeft();
        double newAmountLeft = currentAmountLeft - expenseAmount;
        budget.setBudgetAmountLeft(newAmountLeft);
        if (newAmountLeft == 0) {
            budget.setActive(false);
        }

        return expense;
    }
}

