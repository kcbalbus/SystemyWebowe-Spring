package com.example.lista12.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @Column(unique = true)
    @NotBlank
    @Min(1)
    private String categoryCode;
    @NotBlank
    private String categoryName;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    private List<Product> products;
}
