package com.example.library_backend.library_backend.bl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.library_backend.library_backend.bl.utils.ErrorLogger;
import com.example.library_backend.library_backend.dao.AuthorDao;
import com.example.library_backend.library_backend.dto.AuthorDto;
import com.example.library_backend.library_backend.model.Author;
import com.example.library_backend.library_backend.model.Gender;

import jakarta.persistence.QueryTimeoutException;

public class AuthorBl {
    private AuthorDao authorDao;

    @Autowired
    public AuthorBl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public Optional<List<AuthorDto>> findAllAuthors() {
        try {
            List<Author> authors = authorDao.findAll();
            if (!authors.isEmpty()) {
                return Optional.of(authors.stream().map(this::mapToAuthorDto).collect(Collectors.toList()));
            }
            return Optional.of(Collections.emptyList());
        } catch (DataAccessException e) {
            ErrorLogger.logError("Author BL: Data access exception while retrieving authors", e);
        } catch (QueryTimeoutException e) {
            ErrorLogger.logError("Author BL: Query timeout exception while retrieving authors", e);
        } catch (Exception e) {
            ErrorLogger.logError("Author BL: Exception while retrieving authors", e);
        }
        return Optional.empty();
    }

    public Optional<AuthorDto> findAuthorById(Integer id) {
        try {
            Optional<Author> authorOpt = authorDao.findById(id);
            if (!authorOpt.isPresent()) {
                Exception e = new IllegalArgumentException();
                ErrorLogger.logError("Author not found!", authorOpt.orElse(null), e);
            }
            return Optional.of(authorOpt.map(this::mapToAuthorDto).orElse(null));
        } catch (DataAccessException e) {
            ErrorLogger.logError("Author BL: Data access exception while retrieving author", e);
        } catch (QueryTimeoutException e) {
            ErrorLogger.logError("Author BL: Query timeout exception while retrieving author", e);
        } catch (Exception e) {
            ErrorLogger.logError("Author BL: Exception while retrieving author", e);
        }
        return Optional.empty();
    }

    public Optional<Author> createAuthorDto(AuthorDto authorDto) {
        Author author = mapToAuthor(authorDto);
        try {
            Author savedAuthor = authorDao.save(author);
            return Optional.of(savedAuthor);
        } catch (DataIntegrityViolationException e) {
            ErrorLogger.logError("Author BL: Data integrity violation: {}", authorDto, e);
        } catch (Exception e) {
            ErrorLogger.logError("Author BL: An unexpected error occurred", authorDto, e);
        }
        return Optional.empty();
    }

    public Optional<Author> updateAuthor(AuthorDto authorDto) {
        Optional<Author> authorOpt = authorDao.findById(authorDto.getId());
        if (!authorOpt.isPresent()) {
            Exception e = new IllegalArgumentException();
            ErrorLogger.logError("Author not found!", authorDto, e);
        }
        Author author = mapToAuthor(authorDto);
        try {
            Author savedAuthor = authorDao.save(author);
            return Optional.of(savedAuthor);
        } catch (Exception e) {
            ErrorLogger.logError("Author BL: error while updating a author", authorDto, e);
        }
        return Optional.empty();
    }

    public void deleteAuthor(Integer id) {
        try {
            authorDao.deleteById(id);
        } catch (Exception e) {
            ErrorLogger.logError("Author BL: Error while deleting an author", e);
        }
    }

    // method to insert information to database
    private Author mapToAuthor(final AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName());
        author.setLastname(authorDto.getLastname());
        author.setBirthDate(authorDto.getBirthDate());

        Gender gender = new Gender();
        gender.setId(authorDto.getGenderId());
        gender.setName(authorDto.getGenderName());

        author.setGender(gender);

        return author;
    }

    // method to get information from database
    private AuthorDto mapToAuthorDto(final Author author) {
        return new AuthorDto(
                author.getId(),
                author.getName(),
                author.getLastname(),
                author.getGender().getId(),
                author.getGender().getName(),
                author.getBirthDate());
    }
}
