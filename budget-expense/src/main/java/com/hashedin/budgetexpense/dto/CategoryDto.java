package com.hashedin.budgetexpense.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {

	@NotEmpty(message = "Category name must not be empty")
	@Size(min = 3, message = "Category name must have at least 3 letters")
	private String categoryName;

	private String categoryDescription;
}
