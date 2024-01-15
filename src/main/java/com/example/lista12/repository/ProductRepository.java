package com.example.lista12.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lista12.entity.Product;
public interface ProductRepository extends JpaRepository<Product, Long> {

}

