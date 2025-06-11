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
@Table(name = "departments" )
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne
    @JoinColumn( name="departments_manager")
    private EmployeeEntity manager;

    @OneToMany(mappedBy = "workerDepartment", fetch = FetchType.EAGER)
    private Set<EmployeeEntity> workers;

    @ManyToMany(mappedBy = "freelanceDepartment")
    private Set<EmployeeEntity> freelancers;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle());
    }
}
