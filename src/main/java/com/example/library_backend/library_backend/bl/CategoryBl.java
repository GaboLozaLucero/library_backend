package com.example.library_backend.library_backend.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.library_backend.library_backend.dao.CategoryDao;
import com.example.library_backend.library_backend.dto.CategoryDto;
import com.example.library_backend.library_backend.model.Category;

import jakarta.persistence.QueryTimeoutException;

import com.example.library_backend.library_backend.bl.utils.ErrorLogger;

@Service
public class CategoryBl {

    private CategoryDao categoryDao;

    @Autowired
    public CategoryBl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public Optional<List<CategoryDto>> findAllCategories() {
        try {
            List<Category> categories = categoryDao.findAll();
            if (!categories.isEmpty()) {
                return Optional.of(categories.stream().map(this::mapToCategoryDto).collect(Collectors.toList()));
            }
            return Optional.of(Collections.emptyList());
        } catch (DataAccessException e) {
            ErrorLogger.logError("Category BL: Data access exception while retrieving categories", e);
        } catch (QueryTimeoutException e) {
            ErrorLogger.logError("Category BL: Query timeout exception while retrieving categories", e);
        } catch (Exception e) {
            ErrorLogger.logError("Category BL: Exception while retrieving categories", e);
        }
        return Optional.empty();
    }

    public Optional<Category> createCategory(CategoryDto categoryDto) {
        Category category = mapToCategory(categoryDto);
        try {
            Category savedCategory = categoryDao.save(category);
            return Optional.of(savedCategory);
        } catch (DataIntegrityViolationException e) {
            ErrorLogger.logError("Category BL: Data integrity violation while creating a category: {}", categoryDto, e);
        } catch (DataAccessException e) {
            ErrorLogger.logError("Category BL: Data access exception while creating a category: {}", categoryDto, e);
        } catch (Exception e) {
            ErrorLogger.logError("Category BL: An unexpected error occurred while creating a category: {}", categoryDto,
                    e);
        }
        return Optional.empty();
    }

    public Optional<Category> updateCategory(CategoryDto categoryDto) {
        Category category = mapToCategory(categoryDto);
        try {
            Category updatedCategory = categoryDao.save(category);
            return Optional.of(updatedCategory);
        } catch (Exception e) {
            ErrorLogger.logError("Category BL: An unexpected error occurred while updating a category: {}", categoryDto,
                    e);
        }
        return Optional.empty();
    }

    public void deleteCategory(int id) {
        try {
            categoryDao.deleteById(id);
        } catch (Exception e) {
            ErrorLogger.logError("Category BL: error while deleting a category", e);
        }
    }

    private Category mapToCategory(final CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;

    }

    private CategoryDto mapToCategoryDto(final Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
