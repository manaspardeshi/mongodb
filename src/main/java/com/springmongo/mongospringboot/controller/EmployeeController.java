package com.springmongo.mongospringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmongo.mongospringboot.domain.Employee;
import com.springmongo.mongospringboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper jsonObjectMapper;
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/all")
    public ResponseEntity<Employee> getAllEmployee() {
        List<Employee> emp = employeeService.getAllEmployee();
        return new ResponseEntity<>((Employee) emp, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saveEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }


}
