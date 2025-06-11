package com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.controllers;


import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.entities.DepartmentEntity;
import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.services.DepartmentService;
import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {


    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }


//    public DepartmentController(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }

    @GetMapping("/{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId){
        return departmentService.getDepartmentBtId(departmentId);
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity){
        return departmentService.createNewDepartment(departmentEntity);
    }

    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable  Long employeeId){
        return departmentService.assignManagerToDepartment(departmentId, employeeId);
    }

    @GetMapping(path = "/assignedDepartmentOfManager/{employeeId}")
    public DepartmentEntity getAssignedDepartmentOfManager(@PathVariable Long employeeId){
        return departmentService.getAssignedDepartmentOfManager(employeeId);
    }

    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable  Long employeeId){
        return departmentService.assignWorkerToDepartment(departmentId, employeeId);
    }

    @PutMapping(path = "/{departmentId}/freelancer/{employeeId}")
    public DepartmentEntity assignFreelancerToDepartment(@PathVariable Long departmentId,
                                                     @PathVariable  Long employeeId){
        return departmentService.assignfreelancerToDepartment(departmentId, employeeId);
    }
}
