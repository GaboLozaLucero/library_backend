package com.example.library_backend.library_backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class AuthorDto {
    
    @NotNull(message = "ID cannot be null")
    @Min(value = 1, message = "ID must be greater than or equal to 1")
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 30, message = "Name cannot be longer than 30 characters")
    private String name;

    @NotBlank(message = "Lastname cannot be blank")
    @Size(max = 30, message = "Lastname cannot be longer than 30 characters")
    private String lastname;

    @NotNull(message = "Gender ID cannot be null")
    @Min(value = 1, message = "Gender ID must be greater than or equal to 1")
    private int genderId;

    @NotBlank(message = "Gender Name cannot be blank")
    @Size(max = 30, message = "Gender Name cannot be longer than 30 characters")
    private String genderName;

    @NotNull(message = "Birth Date cannot be null")
    private LocalDate birthDate;

    //Default constructor
    public AuthorDto(){}

    //Parameterized constructor
    public AuthorDto(Integer id, String name, String lastname, int genderId, String genderName, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.genderId = genderId;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "AuthorDto [id=" + id + ", name=" + name + ", lastname=" + lastname + ", genderId=" + genderId
                + ", birthDate=" + birthDate + "]";
    }

    
}
