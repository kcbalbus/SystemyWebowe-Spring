package com.example.lista12.service;

import com.example.lista12.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.lista12.entity.Category;
import com.example.lista12.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category updateCategory(Category categoryDetails, String id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setCategoryName(categoryDetails.getCategoryName());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }
    public void deleteCategory(String id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public Optional<Category> getCategoryById(String id) {
        return Optional.ofNullable(categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id)));
    }

}
