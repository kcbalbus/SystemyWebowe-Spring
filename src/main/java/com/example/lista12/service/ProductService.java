package com.example.lista12.service;

import com.example.lista12.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.lista12.entity.Product;
import com.example.lista12.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void addProduct(@Valid Product product) {
        productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Product product){
        return getProductById(product.getProductId());
    }


    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Product product) {
        if (!productRepository.existsById(product.getProductId())) {
            throw new ResourceNotFoundException("Product not found with id: " + product.getProductId());
        }
        productRepository.deleteById(product.getProductId());
    }

    public void deleteProductById(long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(long id) {
        return Optional.ofNullable(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id)));
    }

    private boolean isEmpty() {
        return productRepository.count() == 0;
    }

}
