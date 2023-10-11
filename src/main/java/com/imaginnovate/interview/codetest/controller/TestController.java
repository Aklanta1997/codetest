package com.imaginnovate.interview.codetest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imaginnovate.interview.codetest.model.EmployeeModel;
import com.imaginnovate.interview.codetest.model.TaxDeductionResult;
import com.imaginnovate.interview.codetest.service.TestService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/check")
@Validated
public class TestController {
	
	@Autowired
	TestService service;
	
	@PostMapping("/saveemployeedetails")
    public ResponseEntity<EmployeeModel> createEmployee(@Valid @RequestBody EmployeeModel employee) {
		EmployeeModel savedEmployee = service.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
	
	@GetMapping("/tax-deduction")
    public ResponseEntity<List<TaxDeductionResult>> calculateTaxDeduction() {
		
		return new ResponseEntity<>(service.calculateTaxDeduction(), HttpStatus.OK);
	}
	}


