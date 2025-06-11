package com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.controllers;


import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.entities.EmployeeEntity;
import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable Long employeeId){
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity employeeEntity){
        return employeeService.createNewEmployee(employeeEntity);
    }
}
