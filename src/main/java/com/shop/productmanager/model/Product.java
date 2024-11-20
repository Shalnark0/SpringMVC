package com.shop.productmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String imageUrl;
    @Column(nullable = false, updatable = false)
    private String productCode;

    @ManyToOne
    @JoinColumn(name = "category_id") // Связь с категорией
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id") // Связь с поставщиком
    private Supplier supplier;

    @JsonProperty("categoryId")
    public void setCategoryId(Long categoryId) {
        this.category = new Category(categoryId); // Подставить категорию по ID
    }

    @JsonProperty("supplierId")
    public void setSupplierId(Long supplierId) {
        this.supplier = new Supplier(supplierId); // Подставить поставщика по ID
    }

}