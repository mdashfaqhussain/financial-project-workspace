package com.hashedin.budgetexpense.controller;

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

import com.hashedin.budgetexpense.dto.ExpenseDto;
import com.hashedin.budgetexpense.service.IExpenceService;
import com.hashedin.budgetexpense.utility.ResponseModel;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private IExpenceService expenseService;

    @PostMapping("/create")
    public ResponseEntity<ResponseModel> createExpense(@RequestBody ExpenseDto expenseDto) {
        return expenseService.createExpense(expenseDto);
    }

    @PutMapping("/update/{expenseId}")
    public ResponseEntity<ResponseModel> updateExpense(@PathVariable int expenseId, @RequestBody ExpenseDto expenseDto) {
        return expenseService.updateExpense(expenseId, expenseDto);
    }

    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<ResponseModel> deleteExpense(@PathVariable int expenseId) {
        return expenseService.deleteExpense(expenseId);
    }

    @GetMapping("/get/{expenseId}")
    public ResponseEntity<ResponseModel> getExpense(@PathVariable int expenseId) {
        return expenseService.getExpense(expenseId);
    }

    @GetMapping("/getAll/{userId}")
    public ResponseEntity<ResponseModel> getAllExpenses(@PathVariable int userId) {
        return expenseService.getAllExpenses(userId);
    }

    @GetMapping("/getBudgetExpenses/{budgetId}")
    public ResponseEntity<ResponseModel> getBudgetExpenses(@PathVariable int budgetId) {
        return expenseService.getBudgetExpenses(budgetId);
    }
}

