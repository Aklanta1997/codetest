package com.imaginnovate.interview.codetest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imaginnovate.interview.codetest.model.EmployeeModel;

@Repository
public interface TestRepository extends JpaRepository<EmployeeModel, Long> {

	
}
