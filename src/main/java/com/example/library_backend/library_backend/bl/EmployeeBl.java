package com.example.library_backend.library_backend.bl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.library_backend.library_backend.bl.utils.ErrorLogger;
import com.example.library_backend.library_backend.dao.EmployeeDao;
import com.example.library_backend.library_backend.dto.EmployeeDto;
import com.example.library_backend.library_backend.model.Charge;
import com.example.library_backend.library_backend.model.Employee;
import com.example.library_backend.library_backend.model.Gender;

import jakarta.persistence.QueryTimeoutException;

@Service
public class EmployeeBl {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeBl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Optional<List<EmployeeDto>> findAllEmployees(){
        try {
            List<Employee> employees = employeeDao.findAll();
            if(!employees.isEmpty()){
                return Optional.of(employees.stream().map(this::mapToEmployeeDto).collect(Collectors.toList()));
            }
            return Optional.of(Collections.emptyList());
        } catch (DataAccessException e) {
            ErrorLogger.logError("Employee BL: Data access exception while retrieving employees", e);
        } catch (QueryTimeoutException e) {
            ErrorLogger.logError("Employee BL: Query timeout exception while retrieving employees", e);
        } catch (Exception e) {
            ErrorLogger.logError("Employee BL: Exception while retrieving employees", e);
        }
        return Optional.empty();
    }

    public Optional<EmployeeDto> findEmployeeById(String id){
        try {
            // Optional<Employee> employeeOpt = employeeDao.findById(id);
            // if(!employeeOpt.isPresent()){
            //     Exception e = new IllegalArgumentException();
            //     ErrorLogger.logError("Employee BL: Employee not found with id: " + id, e);
            // }
            return Optional.of(employeeDao.findById(id).map(this::mapToEmployeeDto).orElse(null));
        } catch (DataAccessException e) {
            ErrorLogger.logError("Employee BL: Data access exception while retrieving employee", e);
        } catch (QueryTimeoutException e) {
            ErrorLogger.logError("Employee BL: Query timeout exception while retrieving employee", e);
        } catch (Exception e) {
            ErrorLogger.logError("Employee BL: Exception while retrieving employee", e);
        }
        return Optional.empty();
    }

    public Optional<Employee> createEmployee(EmployeeDto employeeDto){
        Employee employee = mapToEmployee(employeeDto);
        try{
            Employee savedEmployee = employeeDao.save(employee);
            return Optional.of(savedEmployee);
        } catch (DataIntegrityViolationException e) {
            ErrorLogger.logError("Employee BL: Data integrity violation while creating employee: ", employeeDto, e);
        } catch (DataAccessException e) {
            ErrorLogger.logError("Employee BL: Data access exception while creating a category: {}", employeeDto, e);
        } catch (Exception e) {
            ErrorLogger.logError("Employee BL: An unexpected error occurred while creating a category: {}", employeeDto, e);
        }
        return Optional.empty();
    }

    public Optional<Employee> updateEmployee(EmployeeDto employeeDto){
        Employee employee = mapToEmployee(employeeDto);
        try {
            Employee updatedEmployee = employeeDao.save(employee);
            return Optional.of(updatedEmployee);
        } catch (Exception e) {
            ErrorLogger.logError("Employee BL: Error while trying to update employee", employeeDto, e);
        }
        return Optional.empty();
    }

    public void deleteEmployee(String id){
        try {
            employeeDao.deleteById(id);
        } catch (Exception e) {
            ErrorLogger.logError("Error while deleting employee", e);
        }
    }

    private Employee mapToEmployee(final EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setName(employeeDto.getName());
        employee.setLastname(employeeDto.getLastname());
        employee.setSalary(employeeDto.getSalary());
        employee.setBirthDate(employeeDto.getBirthDate());

        Gender gender = new Gender();
        gender.setId(employeeDto.getGenderId());
        gender.setName(employeeDto.getGenderName());
        employee.setGender(gender);

        Charge charge = new Charge();
        charge.setId(employeeDto.getChargeId());
        charge.setName(employeeDto.getChargeName());
        employee.setCharge(charge);

        employee.setHireDate(employeeDto.getHireDate());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());

        return employee;
    }

    public EmployeeDto mapToEmployeeDto(final Employee employee) {
        return new EmployeeDto(
            employee.getId(),
            employee.getName(),
            employee.getLastname(),
            employee.getSalary(),
            employee.getBirthDate(),
            employee.getGender().getId(),
            employee.getGender().getName(),
            employee.getCharge().getId(),
            employee.getCharge().getName(),
            employee.getHireDate(),
            employee.getEmail(),
            employee.getPhone()
        );
    }
}
