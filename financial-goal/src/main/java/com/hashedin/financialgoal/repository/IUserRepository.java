package com.hashedin.financialgoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.financialgoal.entity.BudgetUser;

public interface IUserRepository extends JpaRepository<BudgetUser, Integer> {

}
