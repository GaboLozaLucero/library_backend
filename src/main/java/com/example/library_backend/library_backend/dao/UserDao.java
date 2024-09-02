package com.example.library_backend.library_backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library_backend.library_backend.model.User;

@Repository
public interface UserDao extends  JpaRepository<User, String> {


    
}
