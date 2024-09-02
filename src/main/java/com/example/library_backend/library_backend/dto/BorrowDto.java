package com.example.library_backend.library_backend.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.validation.constraints.*;

public class BorrowDto {
    
    @NotNull(message = "ID cannot be null")
    private Integer id;

    @NotBlank(message = "User ID cannot be blank")
    @Size(max = 20, message = "User ID cannot be longer than 20 characters")
    private String userId;

    @NotBlank(message = "Employee ID cannot be blank")
    @Size(max = 20, message = "Employee ID cannot be longer than 20 characters")
    private String employeeId;

    @NotBlank(message = "Book ID cannot be blank")
    @Size(max = 13, message = "Book ID cannot be longer than 13 characters")
    private String bookId;

    @NotBlank(message = "Transaction Type cannot be blank")
    @Pattern(regexp = "borrow|return", message = "Transaction Type must be 'borrow' or 'return'")
    private String transactionType;

    @NotNull(message = "Borrow Date cannot be null")
    private Timestamp borrowDate;

    private LocalDate expectedReturnDate;

    private Timestamp returnDate;

    @NotNull(message = "Fine cannot be null")
    @Min(value = 0, message = "Fine must be greater than or equal to 0")
    private BigDecimal fine;

    //Default constructor
    public BorrowDto(){}

    //Parameterized constructor
    public BorrowDto(Integer id, String userId, String employeeId, String bookId, String transactionType,
            Timestamp borrowDate, LocalDate expectedReturnDate, Timestamp returnDate, BigDecimal fine) {
        this.id = id;
        this.userId = userId;
        this.employeeId = employeeId;
        this.bookId = bookId;
        this.transactionType = transactionType;
        this.borrowDate = borrowDate;
        this.expectedReturnDate = expectedReturnDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Timestamp getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Timestamp borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "BorrowDto [id=" + id + ", userId=" + userId + ", employeeId=" + employeeId + ", bookId=" + bookId
                + ", transactionType=" + transactionType + ", borrowDate=" + borrowDate + ", expectedReturnDate="
                + expectedReturnDate + ", returnDate=" + returnDate + ", fine=" + fine + "]";
    }

    
}
