package com.example.lista12.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lista12.entity.Category;
public interface CategoryRepository extends JpaRepository<Category, String> {
}
