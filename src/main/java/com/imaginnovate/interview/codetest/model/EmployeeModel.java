package com.imaginnovate.interview.codetest.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="employee_master")
public class EmployeeModel {
    @NotNull
    @Column(unique = true)
    private String employeeId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    private String email;

    @ElementCollection
    private List<String> phoneNumbers;

    @NotNull
    private LocalDate doj;

    @NotNull
    @DecimalMin("0.0")
    private double salary;


}


}
