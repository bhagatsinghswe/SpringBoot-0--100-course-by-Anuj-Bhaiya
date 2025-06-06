package com.bhagat.springbootweek2.Spring.Boot.Week2.controllers;


import com.bhagat.springbootweek2.Spring.Boot.Week2.dto.EmployeeDTO;
import com.bhagat.springbootweek2.Spring.Boot.Week2.entities.EmployeeEntity;
import com.bhagat.springbootweek2.Spring.Boot.Week2.exceptions.ResourceNotFoundException;
import com.bhagat.springbootweek2.Spring.Boot.Week2.repositories.EmployeeRepository;
import com.bhagat.springbootweek2.Spring.Boot.Week2.services.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmplopyeeController {

//    private final EmployeeRepository employeeRepository;
//
//    public EmplopyeeController(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
    private final EmployeeService employeeService;

    public EmplopyeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


//    @GetMapping(path="/getmessage")
//    public String getMessage(){
//        return "get msg";
//    }

@GetMapping(path= "/{employeeId}")
//public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){
////    return new EmployeeDTO(id,"b2z","abc@gmail.com", 22, LocalDate.of(2025,5,6),true);
//    return employeeService.getEmployeeById(id);
//}
public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable (name="employeeId") Long id){
    Optional<EmployeeDTO> employeeDTOOptional = employeeService.getEmployeeById(id);
    return employeeDTOOptional
            .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
            .orElseThrow(()-> new ResourceNotFoundException("Employee not found with id: "+id));
}

@GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                                @RequestParam(required = false) String sort){
//    return "hi age "+age+" "+sort;
//    return employeeService.getAllEmployees();
    return ResponseEntity.ok(employeeService.getAllEmployees());

}

@PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
//    inputEmployee.setId(100L);
//    return inputEmployee;
//    return employeeRepository.save(inputEmployee);
    EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
}

@PutMapping( path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,
                                          @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
}

@DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById (@PathVariable Long employeeId){
//        return employeeService.deleteEmployeeByID(employeeId);

    Boolean gotDeleted= employeeService.deleteEmployeeByID(employeeId);
    if(gotDeleted) return ResponseEntity.ok(true);
    return ResponseEntity.notFound().build();
}

@PatchMapping(path = "{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeByID(@RequestBody Map<String, Object> updates,
                                                 @PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        if(employeeDTO == null)  return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
}



}
