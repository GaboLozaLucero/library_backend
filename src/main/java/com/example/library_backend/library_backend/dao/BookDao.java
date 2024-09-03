package com.example.library_backend.library_backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library_backend.library_backend.model.Book;

@Repository
public interface BookDao extends  JpaRepository<Book, String> {
    public Optional<Book> findByTitle(String title);
    public Boolean existsByTitle(String title);
}
