package com.example.lista12;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.lista12.repository.CategoryRepository;
import com.example.lista12.service.CategoryService;
import com.example.lista12.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setCategoryName("Pieczywo");
        category.setCategoryCode("K1");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category created = categoryService.addCategory(category);

        assertNotNull(created);
        assertEquals("Pieczywo", created.getCategoryName());
    }

    @Test
    void testGetCategory() {
        Category category = new Category();
        category.setCategoryName("Pieczywo");
        category.setCategoryCode("K1");

        when(categoryRepository.findById("K1")).thenReturn(java.util.Optional.of(category));

        Category found = categoryService.getCategoryById("K1").get();
        assertNotNull(found);
        assertEquals("Pieczywo", found.getCategoryName());
    }

    @Test
    void testUpdateCategory() {
        Category category = new Category();
        category.setCategoryName("Pieczywo");
        category.setCategoryCode("K1");

        when(categoryRepository.findById("K1")).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category updated = categoryService.updateCategory(category, "K1");
        assertNotNull(updated);
        assertEquals("Pieczywo", updated.getCategoryName());
    }

    @Test
    void testDeleteCategory() {
        String categoryId = "K1";

        when(categoryRepository.existsById(categoryId)).thenReturn(true);

        categoryService.deleteCategoryById(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }
}
