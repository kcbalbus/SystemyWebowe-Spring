    package com.example.lista12.service;
    import com.example.lista12.ResourceNotFoundException;
    import jakarta.validation.Valid;
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
        public Category addCategory(@Valid Category category) {
            return categoryRepository.save(category);
        }
        public List<Category> getAllCategories() {
            return categoryRepository.findAll();
        }
        public void updateCategory(Category category) {
           categoryRepository.save(category);
        }

        public void deleteCategory(Category category) {
            if (!categoryRepository.existsById(category.getCategoryCode())) {
                throw new ResourceNotFoundException("Product not found with id: " + category.getCategoryCode());
            }
            categoryRepository.deleteById(category.getCategoryCode());
        }

        public void deleteCategoryById(String id) {
            if (!categoryRepository.existsById(id)) {
                throw new ResourceNotFoundException("Category not found with id: " + id);
            }
            categoryRepository.deleteById(id);
        }

        public Optional<Category> getCategoryById(String id) {
            return Optional.ofNullable(categoryRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id)));
        }


        public Optional<Category> getCategory(Category category){
            return getCategoryById(category.getCategoryCode());
        }


        private boolean isEmpty() {
            return categoryRepository.count() == 0;
        }

    }
