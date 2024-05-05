package com.hashedin.budgetexpense.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int budgetId;

	private String budgetName;

	private LocalDate startDate;

	private LocalDate endDate;

	private double budgetAllocatedAmount;

	private double budgetAmountLeft;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	private boolean isActive;

	@OneToMany(mappedBy = "budget")
	private List<Expense> expenses;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id")
	private BudgetUser user;

	public int getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(int budgetId) {
		this.budgetId = budgetId;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public double getBudgetAllocatedAmount() {
		return budgetAllocatedAmount;
	}

	public void setBudgetAllocatedAmount(double budgetAllocatedAmount) {
		this.budgetAllocatedAmount = budgetAllocatedAmount;
	}

	public double getBudgetAmountLeft() {
		return budgetAmountLeft;
	}

	public void setBudgetAmountLeft(double budgetAmountLeft) {
		this.budgetAmountLeft = budgetAmountLeft;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public BudgetUser getUser() {
		return user;
	}

	public void setUser(BudgetUser user) {
		this.user = user;
	}

	
	
}
