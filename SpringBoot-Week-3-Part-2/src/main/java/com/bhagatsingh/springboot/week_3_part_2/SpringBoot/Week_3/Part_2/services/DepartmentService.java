package com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.services;


import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.entities.DepartmentEntity;
import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.entities.EmployeeEntity;
import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.repositories.DepartmentRepository;
import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository,
                             EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }


    public DepartmentEntity createNewDepartment( DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity getDepartmentBtId(Long id){
        return departmentRepository.findById(id).orElse(null);
    }


    public DepartmentEntity assignManagerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity>  departmentEntity= departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    department.setManager(employee);
                    return departmentRepository.save(department);
                })).orElse(null);
    }


    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId) {
//        Optional<EmployeeEntity> employeeEntity= employeeRepository.findById(employeeId);
//        return employeeEntity.map(employee-> employee.getManagedDepartment()).orElse(null);

//        Optional<EmployeeEntity> employeeEntity= employeeRepository.findById(employeeId);
//        return departmentRepository.findByManager(employeeEntity.get());

        EmployeeEntity  employeeEntity = EmployeeEntity.builder().id(employeeId).build();
        return departmentRepository.findByManager(employeeEntity);
    }

    public DepartmentEntity assignWorkerToDepartment(Long departmentId, Long employeeId) {
        Optional<DepartmentEntity>  departmentEntity= departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {
                    employee.setWorkerDepartment(department);
                    employeeRepository.save(employee);

                    department.getWorkers().add(employee);
                    return department;
                })).orElse(null);

    }

    public DepartmentEntity assignfreelancerToDepartment(Long departmentId, Long employeeId) {

        Optional<DepartmentEntity>  departmentEntity= departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(department ->
                employeeEntity.map(employee -> {

                    employee.getFreelanceDepartment().add(department);
                    employeeRepository.save(employee);

                    department.getFreelancers().add(employee);
                    return department;
                })).orElse(null);

    }
}
