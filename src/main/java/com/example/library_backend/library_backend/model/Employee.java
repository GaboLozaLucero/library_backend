package com.example.library_backend.library_backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "lastname", nullable = false, length = 30)
    private String lastname;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "charge_id", nullable = false)
    private Charge charge;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    public Employee() {}

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Charge getCharge(){
        return charge;
    }

    public void setCharge(Charge charge){
        this.charge = charge;
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

    
}
