package com.example.library_backend.library_backend.dto;

import jakarta.validation.constraints.*;

public class CategoryDto {
    
    @NotNull(message = "ID cannot be null")
    private int id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    public CategoryDto(){}

    public CategoryDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryDto [id=" + id + ", name=" + name + "]";
    }

    
}
