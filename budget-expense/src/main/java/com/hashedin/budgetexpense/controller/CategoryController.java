package com.hashedin.budgetexpense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.budgetexpense.dto.CategoryDto;
import com.hashedin.budgetexpense.service.ICategoryService;
import com.hashedin.budgetexpense.utility.ResponseModel;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseModel> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.createCategory(categoryDto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseModel> getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseModel> updateCategory(@PathVariable int id, @Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseModel> deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }
	

}
