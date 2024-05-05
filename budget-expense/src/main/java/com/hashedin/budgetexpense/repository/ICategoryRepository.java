package com.hashedin.budgetexpense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.budgetexpense.entity.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer>{

}
