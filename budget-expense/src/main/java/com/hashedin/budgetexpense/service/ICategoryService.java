package com.hashedin.budgetexpense.service;

import org.springframework.http.ResponseEntity;

import com.hashedin.budgetexpense.dto.CategoryDto;
import com.hashedin.budgetexpense.utility.ResponseModel;


public interface ICategoryService {

	ResponseEntity<ResponseModel> createCategory(CategoryDto categoryDto);
	ResponseEntity<ResponseModel> getCategoryById(int categoryId);
	ResponseEntity<ResponseModel> updateCategory(int categoryId, CategoryDto categoryDto);
	ResponseEntity<ResponseModel> deleteCategory(int categoryId);
}
