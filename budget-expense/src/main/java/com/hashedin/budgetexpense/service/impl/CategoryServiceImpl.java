package com.hashedin.budgetexpense.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hashedin.budgetexpense.constant.ProjectConstants;
import com.hashedin.budgetexpense.dto.CategoryDto;
import com.hashedin.budgetexpense.entity.Category;
import com.hashedin.budgetexpense.exception.ResourceNotFoundException;
import com.hashedin.budgetexpense.repository.ICategoryRepository;
import com.hashedin.budgetexpense.service.ICategoryService;
import com.hashedin.budgetexpense.utility.Mapper;
import com.hashedin.budgetexpense.utility.ResponseModel;
import com.hashedin.budgetexpense.utility.ResponseUtility;

@Service
public final class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository categoryRepository;

	@Autowired
	ResponseUtility responseUtility;

	@Override
	public ResponseEntity<ResponseModel> createCategory(CategoryDto categoryDto) {
		try {
			Category category = Mapper.convertToCategory(categoryDto);
			categoryRepository.save(category);
			return responseUtility.createSuccessResponse(ProjectConstants.CATEGORY_CREATED_MESSAGE_SUCCESS, null);
		} catch (Exception e) {
			return responseUtility.createErrorResponse(ProjectConstants.CATEGORY_CREATED_MESSAGE_ERROR);
		}
	}

	@Override
	public ResponseEntity<ResponseModel> getCategoryById(int categoryId) {
		try {
			Category category = categoryRepository.findById(categoryId)
					.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

			return responseUtility.createSuccessResponse("Category found", category);
		} catch (ResourceNotFoundException e) {
			return responseUtility.createErrorResponse(ProjectConstants.CATEGORY_FOUND_ERROR + e.getMessage());
		} catch (Exception e) {
			return responseUtility
					.createErrorResponse(ProjectConstants.ERROR_CODE + "Failed to get category: " + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ResponseModel> updateCategory(int categoryId, CategoryDto categoryDto) {
		try {
			Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(
					() -> new ResourceNotFoundException(ProjectConstants.RESOURCE_NOT_FOUND + " " + categoryId));

			Category category = Mapper.updateCategoryFromDto(categoryDto, existingCategory);

			categoryRepository.save(category);

			return responseUtility.createSuccessResponse(ProjectConstants.CATEGORY_UPDATED_ERROR, category);
		} catch (ResourceNotFoundException e) {
			return responseUtility.createErrorResponse(ProjectConstants.RESOURCE_NOT_FOUND);
		} catch (Exception e) {
			return responseUtility.createErrorResponse(ProjectConstants.ERROR_STATUS);
		}
	}

	@Override
	public ResponseEntity<ResponseModel> deleteCategory(int categoryId) {
		try {
			Category category = categoryRepository.findById(categoryId).orElseThrow(
					() -> new ResourceNotFoundException(ProjectConstants.RESOURCE_NOT_FOUND + " " + categoryId));
			categoryRepository.delete(category);
			return responseUtility.createSuccessResponse(ProjectConstants.CATEGORY_DELETED_ERROR, category);
		} catch (ResourceNotFoundException e) {
			return responseUtility.createErrorResponse(ProjectConstants.RESOURCE_NOT_FOUND);
		} catch (Exception e) {
			return responseUtility.createErrorResponse(ProjectConstants.ERROR_STATUS);
		}
	}

}
