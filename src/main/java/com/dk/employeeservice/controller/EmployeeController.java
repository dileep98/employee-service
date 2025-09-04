package com.dk.employeeservice.controller;

import com.dk.employeeservice.entity.Employee;
import com.dk.employeeservice.repo.EmployeeRepository;
import com.dk.employeeservice.response.EmployeeResponse;
import com.dk.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;

@RestController
public class EmployeeController {

    private final RestTemplate restTemplate;

    private final EmployeeService employeeService;

    public EmployeeController(RestTemplate restTemplate, EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.restTemplate = restTemplate;
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public String getEmployee() {
        String address = restTemplate.getForObject("http://localhost:8080/address", String.class);
        return "Name: DileepKumar Tutika, email: dk26092@gmail.com "+ address;
    }

    @GetMapping ("/employees/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(id));
    }
}
