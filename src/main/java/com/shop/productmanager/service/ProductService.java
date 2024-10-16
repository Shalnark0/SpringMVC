package com.shop.productmanager.service;
import java.util.List;

import com.shop.productmanager.exception.ProductNotFoundException;
import com.shop.productmanager.model.Product;
import com.shop.productmanager.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product){
        product.setProductCode(UUID.randomUUID().toString());
        return productRepo.save(product);
    }

    public List<Product> findAllProducts(){
        return productRepo.findAll();
    }

    public Product updateProduct(Product product){
        return productRepo.save(product);
    }

    public Product findProductById(final Long id){
        return productRepo.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product by id " + id + "not found"));
    }

    public void deleteProduct(Long id){
        if(id == null){
            throw new NullPointerException("id cant be null");
        }
        productRepo.deleteProductById(id);
    }
}
