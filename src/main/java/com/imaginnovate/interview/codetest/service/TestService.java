package com.imaginnovate.interview.codetest.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.interview.codetest.model.EmployeeModel;
import com.imaginnovate.interview.codetest.model.TaxDeductionResult;
import com.imaginnovate.interview.codetest.repository.TestRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestService {
	
	@Autowired
	TestRepository testRepository;

	public EmployeeModel saveEmployee(@Valid EmployeeModel employee) {
		log.info("Employee Details inititated:"+employee.toString());
		return testRepository.save(employee);
	}

	public List<TaxDeductionResult> calculateTaxDeduction() {
		List<TaxDeductionResult>taxResults = new ArrayList<>();
		List<EmployeeModel> employees= testRepository.findAll();
		log.info("Employee Details fetched:"+employees.toString());
		employees.forEach(employee->{
			log.info("Employee Tax Initited:"+employee.getEmployeeId());
			String employeeCode=employee.getEmployeeId();
			double monthlySalary=employee.getSalary();
			LocalDate joinDate=employee.getDoj();
        double totalSalary = getTotalSalary(joinDate,monthlySalary);
        // Initialize tax and cess amounts
        double taxAmount = 0.0;
        double cessAmount = 0.0;
       double count=totalSalary/250000;
        // Apply tax slabs
        if (count==1) {
        	taxAmount=0.0;
        }
        	else if (count==2) {
            taxAmount = 250000*0.05;
        } else if (count==3) {
            taxAmount = 250000 * 0.05 + 250000 * 0.10;
        } else if (count>=4) {
            taxAmount = 250000 * 0.05 + 250000 * 0.10 + (totalSalary - 1000000) * 0.20;
        }
        
        

        // Apply cess for the amount more than 2500000
        if (totalSalary > 2500000) {
            double excessAmount = totalSalary - 2500000;
            cessAmount = excessAmount * 0.02;
        }

        TaxDeductionResult result = new TaxDeductionResult();
        result.setEmployeeCode(employeeCode);
        result.setYearlySalary(totalSalary);
        result.setFirstName(employee.getFirstName()); 
        result.setLastName(employee.getLastName());
        result.setTaxAmount(taxAmount);
        result.setCessAmount(cessAmount);
        taxResults.add(result);
		});
        return taxResults;
    }
	
	
	
	 public double getTotalSalary(LocalDate joinDate,double sal) {
		 double joinedMonthSal=0.0;
	        if (joinDate != null) {
	            LocalDate today = LocalDate.now();
	            if (today.isBefore(joinDate)) {
	                return 0.0; // Employee has not joined yet
	            } else {
	            	int joiningMonthTotalDays=joinDate.lengthOfMonth();
	            	int joinedMonthWorkedDays=joinDate.getDayOfMonth();
	            	if(joiningMonthTotalDays!=joinedMonthWorkedDays) {
	            	joinedMonthSal=(sal/joiningMonthTotalDays)*(joiningMonthTotalDays-joinedMonthWorkedDays+1);
	            	}
	                int joiningYear = joinDate.getYear();
	                int joiningMonthValue = joinDate.getMonthValue();
	                int currentYear = today.getYear();
	                int currentMonthValue = today.getMonthValue();
	                double monthsWorked = Double.valueOf((currentYear - joiningYear) * 12 + (currentMonthValue - joiningMonthValue));
	                return (sal*monthsWorked)+ joinedMonthSal;
	            }
	        } else {
	            return 0.0; // DOJ is not set
	        }
	    }
}