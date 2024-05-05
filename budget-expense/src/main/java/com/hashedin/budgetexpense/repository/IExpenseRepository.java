package com.hashedin.budgetexpense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.budgetexpense.entity.Expense;

public interface IExpenseRepository extends JpaRepository<Expense, Integer> {

}
