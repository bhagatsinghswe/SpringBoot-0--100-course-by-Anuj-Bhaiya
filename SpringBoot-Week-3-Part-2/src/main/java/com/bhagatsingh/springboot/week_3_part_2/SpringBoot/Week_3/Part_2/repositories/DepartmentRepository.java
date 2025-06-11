package com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.repositories;


import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.entities.DepartmentEntity;
import com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    DepartmentEntity findByManager(EmployeeEntity employeeEntity);
}
