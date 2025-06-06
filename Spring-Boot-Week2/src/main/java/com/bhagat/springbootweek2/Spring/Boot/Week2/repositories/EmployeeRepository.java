package com.bhagat.springbootweek2.Spring.Boot.Week2.repositories;

import com.bhagat.springbootweek2.Spring.Boot.Week2.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {


}
