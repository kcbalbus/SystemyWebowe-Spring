package com.example.lista12.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @Column(unique = true)
    @Min(1)
    private long productId;
    @Column(nullable = false)
    @NotBlank
    private String productName;
    @DecimalMin("0.0")
    private double weight;
    @DecimalMin("0.0")
    private double price;

    @ManyToOne
    @JoinColumn (name = "category")
    private Category category;
}
