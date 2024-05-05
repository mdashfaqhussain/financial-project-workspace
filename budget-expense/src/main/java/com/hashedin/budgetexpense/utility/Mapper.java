package com.hashedin.budgetexpense.utility;

import com.hashedin.budgetexpense.dto.BudgetDto;
import com.hashedin.budgetexpense.dto.CategoryDto;
import com.hashedin.budgetexpense.dto.ExpenseDto;
import com.hashedin.budgetexpense.entity.Budget;
import com.hashedin.budgetexpense.entity.BudgetUser;
import com.hashedin.budgetexpense.entity.Category;
import com.hashedin.budgetexpense.entity.Expense;

public class Mapper {

	public static Category convertToCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setCategoryName(categoryDto.getCategoryName());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		return category;
	}

	public static Category updateCategoryFromDto(CategoryDto categoryDto, Category category) {
		if (categoryDto.getCategoryName() != null) {
			category.setCategoryName(categoryDto.getCategoryName());
		}
		if (categoryDto.getCategoryDescription() != null) {
			category.setCategoryDescription(categoryDto.getCategoryDescription());
		}
		return category;
	}

	public static Budget createBudgetFromDto(BudgetDto budgetDto, BudgetUser user) {
		Budget budget = new Budget();
		budget.setBudgetName(budgetDto.getBudgetName());
		budget.setStartDate(budgetDto.getStartDate());
		budget.setEndDate(budgetDto.getEndDate());
		budget.setBudgetAllocatedAmount(budgetDto.getBudgetAllocatedAmount());
		budget.setUser(user);
		return budget;
	}

	public static void mapBudgetDtoToBudget(Budget budget, BudgetDto budgetDto) {
		budget.setBudgetName(budgetDto.getBudgetName());
		budget.setStartDate(budgetDto.getStartDate());
		budget.setEndDate(budgetDto.getEndDate());
		budget.setBudgetAllocatedAmount(budgetDto.getBudgetAllocatedAmount());
	}

	public static BudgetDto mapBudgetToDto(Budget budget) {
		BudgetDto budgetDto = new BudgetDto();
		budgetDto.setBudgetName(budget.getBudgetName());
		budgetDto.setStartDate(budget.getStartDate());
		budgetDto.setEndDate(budget.getEndDate());
		budgetDto.setBudgetAllocatedAmount(budget.getBudgetAllocatedAmount());
		return budgetDto;
	}

	public static Expense mapDtoToEntity(ExpenseDto expenseDto) {
		Expense expense = new Expense();
		expense.setExpenseAmount(expenseDto.getExpenseAmount());
		BudgetUser user = new BudgetUser();
		user.setId(expenseDto.getUserId());
		expense.setUser(user);
		return expense;
	}

	public static ExpenseDto mapEntityToDto(Expense expense) {
		ExpenseDto expenseDto = new ExpenseDto();
		expenseDto.setExpenseAmount(expense.getExpenseAmount());
		expenseDto.setUserId(expense.getUser().getId());
		expenseDto.setBudgetId(expense.getBudget().getBudgetId());
		expenseDto.setCategoryId(expense.getCategory().getCategoryId());
		return expenseDto;
	}



}
