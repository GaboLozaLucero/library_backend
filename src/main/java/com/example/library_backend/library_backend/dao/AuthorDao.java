package com.example.library_backend.library_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library_backend.library_backend.model.Author;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer>{
    public Author findAuthorByName(String name, String lastname);
}
