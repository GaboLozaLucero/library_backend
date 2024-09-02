package com.example.library_backend.library_backend.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class BookDto {
    
    @NotBlank(message = "Book ID cannot be blank")
    @Size(max = 13, message = "Book ID cannot be longer than 13 characters")
    private String bookId;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 40, message = "Title cannot be longer than 40 characters")
    private String title;

    @NotNull(message = "Publish Date cannot be null")
    private LocalDate publishDate;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private int quantity;

    @NotNull(message = "Created At cannot be null")
    private Timestamp createdAt;

    //Default constructor
    public BookDto(){}

    //Parameterized constructor
    public BookDto(String bookId, String title, LocalDate publishDate, int quantity, Timestamp createdAt) {
        this.bookId = bookId;
        this.title = title;
        this.publishDate = publishDate;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BookDto [bookId=" + bookId + ", title=" + title + ", publishDate=" + publishDate + ", quantity="
                + quantity + ", createdAt=" + createdAt + "]";
    }

    
}
