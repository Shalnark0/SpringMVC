package com.shop.productmanager.repo;

import com.shop.productmanager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    void deleteProductById(Long id);

    Optional<Product> findProductById(Long id);
}
