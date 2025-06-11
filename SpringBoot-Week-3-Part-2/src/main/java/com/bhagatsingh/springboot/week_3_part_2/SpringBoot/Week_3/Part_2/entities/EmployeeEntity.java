package com.bhagatsingh.springboot.week_3_part_2.SpringBoot.Week_3.Part_2.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "manager")
    @JsonIgnore
    private DepartmentEntity managedDepartment;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "worker_department")
    @JoinTable(name = "worker_department_mapping")
    @JsonIgnore
    private DepartmentEntity workerDepartment;

    @ManyToMany
    @JoinTable(name = "freelancer_department_table",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    @JsonIgnore
    private Set<DepartmentEntity> freelanceDepartment;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
