package com.dk.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EmployeeController {

    private final RestTemplate restTemplate;

    public EmployeeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/employee")
    public String getEmployee() {

        String address = restTemplate.getForObject("http://localhost:8080/address", String.class);
        return "Name: DileepKumar Tutika, email: dk26092@gmail.com "+ address;
    }
}
