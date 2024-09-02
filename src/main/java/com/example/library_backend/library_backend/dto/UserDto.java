package com.example.library_backend.library_backend.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class UserDto {
    
    @NotBlank(message = "ID cannot be blank")
    private String id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Lastname cannot be blank")
    private String lastname;

    @NotNull(message = "BirthDate cannot be null")
    private LocalDate birthDate;

    @NotNull(message = "GenderId cannot be null")
    private int genderId;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 100, message = "Address cannot exceed 100 characters")
    private String address;

    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    private String phone;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    private Timestamp createdAt;

    //Default constructor
    public UserDto(){}

    //Parameterized constructor
    public UserDto(String id, String name, String lastname, LocalDate birthDate, int genderId, String address,
            String phone, String email, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.genderId = genderId;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", name=" + name + ", lastname=" + lastname + ", birthDate=" + birthDate
                + ", genderId=" + genderId + ", address=" + address + ", phone=" + phone + ", email=" + email
                + ", createdAt=" + createdAt + "]";
    }

    
}
