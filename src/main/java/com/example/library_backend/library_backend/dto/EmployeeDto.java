package com.example.library_backend.library_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class EmployeeDto {
    
    @NotBlank(message = "ID cannot be blank")
    private String id;
    
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 30, message = "Name cannot exceed 50 characters")
    private String name;
    
    @NotBlank(message = "Lastname cannot be blank")
    @Size(max = 30, message = "Lastname cannot exceed 50 characters")
    private String lastname;
    
    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be a positive value")
    private BigDecimal salary;
    
    @NotNull(message = "Birth date cannot be null")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;
    
    @NotNull(message = "Gender ID cannot be null")
    private int genderId;
    
    @NotBlank(message = "Gender name cannot be blank")
    private String genderName;
    
    @NotNull(message = "Charge ID cannot be null")
    private int chargeId;
    
    @NotBlank(message = "Charge name cannot be blank")
    private String chargeName;
    
    @NotNull(message = "Hire date cannot be null")
    private LocalDate hireDate;
    
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
    
    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    private String phone;

    //Default constructor
    public EmployeeDto(){}

    //Parameterized constructor
    public EmployeeDto(String id, String name, String lastname, BigDecimal salary, LocalDate birthDate, int genderId, String genderName,
            int chargeId, String chargeName, LocalDate hireDate, String email, String phone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.salary = salary;
        this.birthDate = birthDate;
        this.genderId = genderId;
        this.genderName = genderName;
        this.chargeId = chargeId;
        this.chargeName = chargeName;
        this.hireDate = hireDate;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }
    
    public String getGenderName(){
        return genderName;
    }

    public void setGenderName(String genderName){
        this.genderName = genderName;
    }

    public int getChargeId() {
        return chargeId;
    }

    public void setChargeId(int chargeId) {
        this.chargeId = chargeId;
    }

    public String getChargeName(){
        return chargeName;
    }

    public void setChargeName(String chargeName){
        this.chargeName = chargeName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "EmployeeDto [id=" + id + ", name=" + name + ", lastname=" + lastname + ", salary=" + salary
                + ", birthDate=" + birthDate + ", genderId=" + genderId + ", chargeId=" + chargeId + ", hireDate="
                + hireDate + ", email=" + email + ", phone=" + phone + "]";
    }

    
}
