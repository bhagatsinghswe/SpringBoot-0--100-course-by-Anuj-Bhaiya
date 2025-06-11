package com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.services;


import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.entities.EmployeeEntity;
import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public EmployeeEntity createNewEmployee( EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity getEmployeeById( Long id){
        return employeeRepository.findById(id).orElse(null);
    }
}
