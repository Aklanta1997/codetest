package com.imaginnovate.interview.codetest.model;

import lombok.Data;

@Data
public class TaxDeductionResult {
	
	private String employeeCode;
    private String firstName;
    private String lastName;
    private double yearlySalary;
    private double taxAmount;
    private double cessAmount;

}
