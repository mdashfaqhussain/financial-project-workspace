package com.hashedin.budgetexpense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.budgetexpense.entity.BudgetUser;

public interface IUserRepository  extends JpaRepository<BudgetUser, Integer>{

	boolean existsByName(String name);

}
