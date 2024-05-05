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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.budgetexpense.dto.BudgetDto;
import com.hashedin.budgetexpense.service.IBudgetService;
import com.hashedin.budgetexpense.utility.ResponseModel;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/budget")
public class BudgetController {

	@Autowired
	IBudgetService budgetService;

	@PostMapping("/create-budget")
	public ResponseEntity<ResponseModel> createBudget(@Valid @RequestBody BudgetDto budgetDto) {
		return budgetService.createBudget(budgetDto);
	}

	@DeleteMapping("/delete/{budgetId}")
	public ResponseEntity<ResponseModel> deleteBudget(@PathVariable int budgetId) {
		return budgetService.deleteBudget(budgetId);
	}

	@PutMapping("/update/{budgetId}")
	public ResponseEntity<ResponseModel> updateBudget(@PathVariable int budgetId,
			@Valid @RequestBody BudgetDto budgetDto) {
		return budgetService.updateBudget(budgetId, budgetDto);
	}

	@GetMapping("/all/{userId}")
	public ResponseEntity<ResponseModel> getAllBudgetsByUserId(@PathVariable int userId) {
		return budgetService.getAllBudgetsByUserId(userId);
	}

	@GetMapping("/start-date/{userId}/{startDate}")
	public ResponseEntity<ResponseModel> getBudgetsByStartDate(@PathVariable int userId,
			@PathVariable String startDate) {
		return budgetService.getBudgetsByStartDate(userId, startDate);
	}

	@GetMapping("/end-date/{userId}/{endDate}")
	public ResponseEntity<ResponseModel> getBudgetsByEndDate(@PathVariable int userId, @PathVariable String endDate) {
		return budgetService.getBudgetsByEndDate(userId, endDate);
	}

	@GetMapping("/date-range/{userId}/{startDate}/{endDate}")
	public ResponseEntity<ResponseModel> getBudgetsByDateRange(@PathVariable int userId, @PathVariable String startDate,
			@PathVariable String endDate) {
		return budgetService.getBudgetsByDateRange(userId, startDate, endDate);
	}

	@GetMapping("/{userId}/{budgetId}")
	public ResponseEntity<ResponseModel> getBudget(@PathVariable int userId, @PathVariable int budgetId) {
		return budgetService.getBudget(budgetId, userId);
	}

	@GetMapping("/active")
	public ResponseEntity<ResponseModel> getActiveBudgets(@RequestParam int userId, @RequestParam boolean isActive) {
		return budgetService.getActiveBudgets(userId, isActive);
	}
}
