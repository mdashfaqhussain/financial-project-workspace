package com.hashedin.financialgoal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.financialgoal.dto.FinancialGoalDto;

import com.hashedin.financialgoal.service.IFinancialGoalService;
import com.hashedin.financialgoal.utility.ResponseModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/goal")
public class FinancialGoalRepository {

	@Autowired
	private IFinancialGoalService financialGoalService;

	@PostMapping("/save")
    public ResponseEntity<ResponseModel> saveGoal(@Valid @RequestBody FinancialGoalDto goalDto) {
        return financialGoalService.saveGoal(goalDto);
    }

    @DeleteMapping("/delete/{goalId}")
    public ResponseEntity<ResponseModel> deleteFinancialGoal(@PathVariable int goalId) {
        return financialGoalService.deleteFinancialGoal(goalId);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseModel> getAllFinancialGoals() {
        return financialGoalService.getAllFinancialGoals();
    }

    @PutMapping("/update/{goalId}")
    public ResponseEntity<ResponseModel> updateFinancialGoal(@PathVariable int goalId, @Valid @RequestBody FinancialGoalDto financialGoalDto) {
        return financialGoalService.updateFinancialGoal(goalId, financialGoalDto);
    }
	

}
