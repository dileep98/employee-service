package com.dk.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/employee")
    public String getEmployee() {
        return "Name: DileepKumar Tutika, email: dk26092@gmail.com";
    }
}
