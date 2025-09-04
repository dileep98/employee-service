package com.dk.employeeservice.service;

import com.dk.employeeservice.entity.Employee;
import com.dk.employeeservice.repo.EmployeeRepository;
import com.dk.employeeservice.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeResponse getEmployeeById(int id){

        Employee employee = employeeRepository.findById(id).orElseThrow();
        return modelMapper.map(employee, EmployeeResponse.class);
    }


}
