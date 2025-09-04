package com.dk.employeeservice.service;

import com.dk.employeeservice.entity.Employee;
import com.dk.employeeservice.repo.EmployeeRepository;
import com.dk.employeeservice.response.AddressResponse;
import com.dk.employeeservice.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final WebClient webClient;
    @Value("${address-service.base.url}")
    private String addressRootUrl;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, RestTemplate restTemplate, WebClient webClient) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    public EmployeeResponse getEmployeeById(int id){
//        AddressResponse addressResponse = restTemplate.getForObject(addressRootUrl+"/address/{id}", AddressResponse.class, id);
        AddressResponse addressResponse = webClient
                                            .get()
                                            .uri("/address/"+ id)
                                            .retrieve()
                                            .bodyToMono(AddressResponse.class)
                .block();
        Employee employee = employeeRepository.findById(id).orElseThrow();
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }


}
