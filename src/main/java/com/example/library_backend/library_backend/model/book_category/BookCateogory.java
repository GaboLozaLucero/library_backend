package com.example.library_backend.library_backend.model.book_category;

import com.example.library_backend.library_backend.model.Book;
import com.example.library_backend.library_backend.model.Category;

import jakarta.persistence.*;

@Entity
@Table(name = "book_category")
public class BookCateogory {
    
    @EmbeddedId
    private BookCategoryId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("bookId")
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public BookCategoryId getId() {
        return id;
    }

    public void setId(BookCategoryId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
}
