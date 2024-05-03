package com.hashedin.financialgoal.utility;

import com.hashedin.financialgoal.dto.FinancialGoalDto;
import com.hashedin.financialgoal.entity.FinancialGoal;

public class Mapper {

	public static FinancialGoal convertToEntity(FinancialGoalDto goalDto) {
		FinancialGoal financialGoal = new FinancialGoal();
		financialGoal.setGoalName(goalDto.getGoalName());
		financialGoal.setTotalAmount(goalDto.getTotalAmount());
		financialGoal.setEnteredAmount(goalDto.getEnteredAmount());
		financialGoal.setStartDate(goalDto.getStartDate());
		financialGoal.setEndDate(goalDto.getEndDate());
		financialGoal.setActive(true);
		return financialGoal;
	}
}
