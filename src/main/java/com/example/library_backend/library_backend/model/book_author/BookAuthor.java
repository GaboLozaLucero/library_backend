package com.example.library_backend.library_backend.model.book_author;

import com.example.library_backend.library_backend.model.Author;
import com.example.library_backend.library_backend.model.Book;

import jakarta.persistence.*;

@Entity
@Table(name = "book_author")
public class BookAuthor {

    @EmbeddedId
    private BookAuthorId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("bookId")
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("authorId")
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public BookAuthor(){}

    public BookAuthorId getId() {
        return id;
    }

    public void setId(BookAuthorId id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    
}
