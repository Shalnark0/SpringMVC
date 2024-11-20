package com.shop.productmanager.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String contactInfo;

    @OneToMany(mappedBy = "supplier") // Обратная связь с продуктами
    @JsonIgnore
    private List<Product> products;

    // Конструктор, принимающий только ID
    @JsonCreator
    public Supplier(@JsonProperty("id") Long id) {
        this.id = id;
    }

    // Можно добавить дополнительные методы, если нужно
}
