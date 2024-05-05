package com.hashedin.budgetexpense.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.budgetexpense.entity.Budget;
import com.hashedin.budgetexpense.entity.BudgetUser;

public interface IBudgetRepository extends JpaRepository<Budget, Integer>{
	
	List<Budget> findByUser(BudgetUser user);
	
	List<Budget>  findByUserAndStartDate(BudgetUser user, LocalDate startDate);
	
	List<Budget>  findByUserAndEndDate(BudgetUser user, LocalDate endDate);

	List<Budget> findByUserAndStartDateBetween(BudgetUser user, LocalDate startDate, LocalDate endDate);

	Optional<Budget> findByBudgetIdAndUser(int budgetId, BudgetUser user);
	
	List<Budget> findByUserAndIsActive(BudgetUser user, Boolean isActive);
	

}
