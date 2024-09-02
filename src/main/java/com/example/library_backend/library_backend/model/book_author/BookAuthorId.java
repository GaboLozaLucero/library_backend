package com.example.library_backend.library_backend.model.book_author;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Embeddable
public class BookAuthorId implements Serializable{
    
    @Column(name = "book_id")
    private String bookId;

    @Column(name = "author_id")
    private int authorId;

    public BookAuthorId(){}

    public BookAuthorId(String bookId, int authorId){
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookAuthorId bookAuthorId = (BookAuthorId) obj;
        return authorId == bookAuthorId.authorId && Objects.equals(bookId, bookAuthorId.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, authorId);
    }

}
