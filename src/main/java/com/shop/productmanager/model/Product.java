package com.shop.productmanager.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=false,updatable=false)
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String imageUrl;
    @Column(nullable=false, updatable=false)
    private String productCode;

    public Product(){}

    public Product(String name, String description, Integer price, String imageUrl, String productCode){
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.productCode = productCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString(){
        return "Product{"+
                "id" + id +
                ", name=" + name + '\'' +
                ", description=" + description + '\'' +
                ", price=" + price + '\'' +
                ", imageUrl=" + imageUrl + '\'' + '}';
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}


