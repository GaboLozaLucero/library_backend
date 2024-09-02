package com.example.library_backend.library_backend.bl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.library_backend.library_backend.bl.utils.ErrorLogger;
import com.example.library_backend.library_backend.dao.BookDao;
import com.example.library_backend.library_backend.dto.BookDto;
import com.example.library_backend.library_backend.model.Book;

import jakarta.persistence.QueryTimeoutException;

@Service
public class BookBl {
    
    private BookDao bookDao;

    @Autowired
    public BookBl(BookDao bookDao){
        this.bookDao = bookDao;
    }

    public Optional<List<BookDto>> findAllBooks(){
        try {
            List<Book> books = bookDao.findAll();
            if(!books.isEmpty()){
                return Optional.of(books.stream().map(this::mapToBookDto).collect(Collectors.toList()));
            }
            return Optional.of(Collections.emptyList());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return Optional.empty();
    }

    public Optional<BookDto> findBookByTitle(String title){
        try {
            if(!bookDao.existsByTitle(title)){
                Exception e = new IllegalArgumentException();
                ErrorLogger.logError("No book found", e);
            }
            return Optional.of(mapToBookDto(bookDao.findByTitle(title)));
        } catch (DataAccessException e) {
            ErrorLogger.logError("Book BL: Data access exception while retrieving book", e);
        } catch (QueryTimeoutException e) {
            ErrorLogger.logError("Book BL: Query timeout exception while retrieving book", e);
        } catch (Exception e) {
            ErrorLogger.logError("Book BL: Exception while retrieving book", e);
        }
        return Optional.empty();
    }

    private BookDto mapToBookDto(Book book){
        return new BookDto(
            book.getId(),
            book.getTitle(),
            book.getPublishDate(),
            book.getQuantity(),
            book.getCreatedAt()
        );
    }
}
