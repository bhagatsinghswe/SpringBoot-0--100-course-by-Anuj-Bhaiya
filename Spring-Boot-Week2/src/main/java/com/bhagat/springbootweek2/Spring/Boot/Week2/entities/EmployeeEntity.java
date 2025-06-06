package com.bhagat.springbootweek2.Spring.Boot.Week2.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String role;
    private Double salary;
    private LocalDate dateOfJoining;
    @JsonProperty("inActive")
    private Boolean isActive;

}

//
//package com.bhagat.springbootweek2.Spring.Boot.Week2.entities;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*; // ✅ Required for annotations
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDate;
//
//@Entity
//@Table
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class EmployeeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Size(min = 3, max = 10, message = "Name must be between 3 and 10 characters")
//    private String name;
//
//    @Email(message = "Email should be valid")
//    @NotBlank(message = "Email is mandatory")
//    private String email;
//
//    @Min(value = 18, message = "Age must be at least 18")
//    @Max(value = 80, message = "Age must be at most 80")
//    private Integer age;
//
//    private String role;
//
//    @NotNull(message = "Salary is required")
//    @DecimalMin(value = "100.50", message = "Salary must be at least 100.50")
//    @DecimalMax(value = "100000.99", message = "Salary must be at most 100000.99")
//    @Digits(integer = 6, fraction = 2, message = "Salary must be a valid monetary amount")
//    private Double salary;
//
//    @PastOrPresent(message = "Date of joining cannot be in the future")
//    private LocalDate dateOfJoining;
//
//    @JsonProperty("inActive")
//    private Boolean isActive;
//}
