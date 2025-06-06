package com.bhagat.springbootweek2.Spring.Boot.Week2.services;

import java.lang.reflect.Field;

import com.bhagat.springbootweek2.Spring.Boot.Week2.dto.EmployeeDTO;
import com.bhagat.springbootweek2.Spring.Boot.Week2.entities.EmployeeEntity;
import com.bhagat.springbootweek2.Spring.Boot.Week2.exceptions.ResourceNotFoundException;
import com.bhagat.springbootweek2.Spring.Boot.Week2.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//gpt
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;


@Service
public class EmployeeService {

    @Autowired
    private Validator validator;

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelmapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelmapper) {
        this.employeeRepository = employeeRepository;
        this.modelmapper = modelmapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
//        return modelmapper.map(employeeEntity, EmployeeDTO.class);

//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelmapper.map(employeeEntity1, EmployeeDTO.class));

        return employeeRepository
                .findById(id)
                .map(employeeEntity -> modelmapper.map(employeeEntity,EmployeeDTO.class));

    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelmapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        //to check if user is admin
        //log something
        EmployeeEntity toSaveEntity = modelmapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelmapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
//        boolean exists = isExistsByEmployeeId(employeeId);
//        if(!exists) through new ResourceNotFoundException("Employee not found with id:"+employeeId);
        isExistsByEmployeeId(employeeId);

        EmployeeEntity employeeEntity = modelmapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelmapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    //gpt
//public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
//    Optional<EmployeeEntity> optionalEntity = employeeRepository.findById(employeeId);
//    EmployeeEntity employeeEntity;
//
//    if (optionalEntity.isPresent()) {
//        // Update existing
//        employeeEntity = optionalEntity.get();
//        modelmapper.map(employeeDTO, employeeEntity);
//    } else {
//        // Create new
//        employeeEntity = modelmapper.map(employeeDTO, EmployeeEntity.class);
//        employeeEntity.setId(employeeId);
//    }
//
//    EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
//    return modelmapper.map(savedEmployeeEntity, EmployeeDTO.class);
//}



    public boolean deleteEmployeeByID(Long employeeId) {
//        boolean exists = isExistsByEmployeeId(employeeId);
////        if(!exists) return false;
//        if(!exists) through new ResourceNotFoundException("Employee not found with id:"+employeeDTO);
        isExistsByEmployeeId(employeeId);

        employeeRepository.deleteById(employeeId);
        return true;
    }

//    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
////        boolean exists =isExistsByEmployeeId(employeeId);
//////        if(!exists) return null;
////        if(!exists) through new ResourceNotFoundException("Employee not found with id:"+employeeDTO);
////        EmployeeEntity employeeEntity =employeeRepository.findById(employeeId).get();
//        isExistsByEmployeeId(employeeId);
//
//        updates.forEach((field,value)->{
//            Field fieldToBeUpdated= ReflectionUtils.findField(EmployeeEntity.class, field);
//            fieldToBeUpdated.setAccessible(true);
//            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
//        });
//        return modelmapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
//    }

//public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
//    isExistsByEmployeeId(employeeId);
//    EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
//
//    updates.forEach((field, value) -> {
//        Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
//        if (fieldToBeUpdated == null) {
//            throw new RuntimeException("Field " + field + " not found on EmployeeEntity");
//        }
//        fieldToBeUpdated.setAccessible(true);
//        ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
//    });
//
//    return modelmapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
//}

    //gpt
public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
    isExistsByEmployeeId(employeeId);
    EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

    updates.forEach((field, value) -> {
        Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
        if (fieldToBeUpdated == null) {
            throw new RuntimeException("Field '" + field + "' not found on EmployeeEntity");
        }
        fieldToBeUpdated.setAccessible(true);
        ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
    });

    // ✅ Validate updated entity before saving
    Set<ConstraintViolation<EmployeeEntity>> violations = validator.validate(employeeEntity);
    if (!violations.isEmpty()) {
        throw new ConstraintViolationException(violations);
    }

    // Save and return
    EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
    return modelmapper.map(savedEmployeeEntity, EmployeeDTO.class);
}




//    //    public Boolean isExistsByEmployeeId( Long employeeID){
////        return employeeRepository.existsById(employeeID);
////    }
//
//    public void isExistsByEmployeeId( Long employeeID){
//        boolean exists = employeeRepository.existsById(employeeID);
//        if(!exists) throw new ResourceNotFoundException("Employee not found with id:"+employeeDTO);
////    return true;   // because changed boolean to void( return value never used
//    }

public void isExistsByEmployeeId(Long employeeID) {
    boolean exists = employeeRepository.existsById(employeeID);
    if (!exists) {
        throw new ResourceNotFoundException("Employee not found with id: " + employeeID);
    }
}

}
