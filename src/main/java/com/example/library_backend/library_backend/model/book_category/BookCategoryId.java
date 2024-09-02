package com.example.library_backend.library_backend.model.book_category;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Embeddable
public class BookCategoryId implements Serializable{

    @Column(name = "book_id")
    private String bookId;

    @Column(name = "category_id")
    private int categoryId;
    
    public BookCategoryId(){}

    public BookCategoryId(String bookId, int categoryId){
        this.bookId = bookId;
        this.categoryId = categoryId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookCategoryId bookCategoryId = (BookCategoryId) obj;
        return categoryId == bookCategoryId.categoryId && Objects.equals(bookId, bookCategoryId.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, categoryId);
    }
}
