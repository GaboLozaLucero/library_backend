package com.example.library_backend.library_backend.bl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.library_backend.library_backend.bl.utils.ErrorLogger;
import com.example.library_backend.library_backend.dao.UserDao;
import com.example.library_backend.library_backend.dto.UserDto;
import com.example.library_backend.library_backend.model.Gender;
import com.example.library_backend.library_backend.model.User;

import jakarta.persistence.QueryTimeoutException;

@Service
public class UserBl {

    private UserDao userDao;

    @Autowired
    public UserBl(UserDao userDao){
        this.userDao = userDao;
    }

    public Optional<List<UserDto>> findAllUsers(){
        try {
            List<User> users = userDao.findAll();
            if(!users.isEmpty()){
                return Optional.of(users.stream().map(this::mapToUserDto).collect(Collectors.toList()));
            }
            return Optional.of(Collections.emptyList());
        } catch (DataAccessException e) {
            ErrorLogger.logError("User BL: Data access exception while retrieving users", e);
        } catch (QueryTimeoutException e) {
            ErrorLogger.logError("User BL: Query timeout exception while retrieving users", e);
        } catch (Exception e) {
            ErrorLogger.logError("User BL: Exception while retrieving users", e);
        }
        return Optional.empty();
    }

    public Optional<UserDto> findUserById(String id){
        try {
            if(!userDao.existsById(id)){
                Exception e = new IllegalArgumentException();
                ErrorLogger.logError("User BL: User not found!", e);
            }
            return Optional.of(userDao.findById(id).map(this::mapToUserDto).orElse(null));
        } catch (DataAccessException e) {
            ErrorLogger.logError("User BL: Data access exception while retrieving user", e);
        } catch (QueryTimeoutException e) {
            ErrorLogger.logError("User BL: Query timeout exception while retrieving user", e);
        } catch (Exception e) {
            ErrorLogger.logError("User BL: Exception while retrieving user", e);
        }
        return Optional.empty();
    }

    public Optional<User> createUser(UserDto userDto){
        try {
            User user = userDao.save(mapToUser(userDto));
            return Optional.of(user);
        } catch (DataIntegrityViolationException e) {
            ErrorLogger.logError("User BL: Data integrity violation while creating  user: ", userDto, e);
        } catch (DataAccessException e) {
            ErrorLogger.logError("User BL: Data access exception while creating a user: {}", userDto, e);
        } catch (Exception e) {
            ErrorLogger.logError("User BL: An unexpected error occurred while creating a user: {}", userDto, e);
        }
        return Optional.empty();
    }

    public Optional<UserDto> updateUser(UserDto userDto){
        try {
            if(userDao.existsById(userDto.getId())){
                userDao.save(mapToUser(userDto));
            }
        } catch (Exception e) {
            ErrorLogger.logError("Error while updating user", userDto, e);
        }
        return Optional.empty();
    }

    public void deleteUser(String id){
        try {
            userDao.deleteById(id);
        } catch (Exception e) {
            ErrorLogger.logError("Error while deleting user", e);
        }
    }

    private User mapToUser(final UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setBirthDate(userDto.getBirthDate());

        Gender gender = new Gender();
        gender.setId(userDto.getGenderId());
        user.setGender(gender);

        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setCreatedAt(userDto.getCreatedAt());
        return user;
    }

    private UserDto mapToUserDto(final User user){
        return new UserDto(
            user.getId(),
            user.getName(),
            user.getLastname(),
            user.getBirthDate(),
            user.getGender().getId(),
            user.getAddress(),
            user.getPhone(),
            user.getEmail(),
            user.getCreatedAt()
        ) ;
    }
}
