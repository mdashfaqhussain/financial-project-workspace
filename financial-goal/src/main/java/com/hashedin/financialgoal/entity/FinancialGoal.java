package com.hashedin.financialgoal.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialGoal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int goalId;

	private String goalName;

	private double currentAmount;

	private double totalAmount;

	private double enteredAmount;

	private LocalDate startDate;

	private LocalDate endDate;

	@CreationTimestamp
	private LocalDate createdAt;

	@LastModifiedDate
	private LocalDate updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id")
	private BudgetUser user;
	

	private boolean isActive;

	@PostLoad
	@PostPersist
	@PostUpdate
	private void calculateCurrentAmount() {
	    this.currentAmount = this.totalAmount - this.enteredAmount;
	    if (this.enteredAmount + this.currentAmount == this.totalAmount) {
	        this.isActive = false;
	        this.enteredAmount = 0;
	    }
	}


}
