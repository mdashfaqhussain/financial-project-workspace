package com.hashedin.financialgoal.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.financialgoal.entity.FinancialGoal;

public interface IFinancialGoalRepository extends JpaRepository<FinancialGoal, Integer>{

	FinancialGoal findByGoalName(String goalName);

    List<FinancialGoal> findByStartDate(LocalDate startDate);

    List<FinancialGoal> findByUserId(int userId);

    List<FinancialGoal> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    List<FinancialGoal> findByIsActive(boolean isActive);

    List<FinancialGoal> findByIsActiveNot(boolean isActive);
    
    List<FinancialGoal> findByEndDate(LocalDate endDate);
    
    List<FinancialGoal> findByUserIdAndStartDateBetween(int userId, LocalDate startDate, LocalDate endDate );
}
