package com.shop.productmanager.service;


import com.shop.productmanager.model.Product;
import com.shop.productmanager.repo.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class ProductServiceTest {
    Product product;
    private ProductRepo productRepo;

    private ProductService productService;

    @BeforeEach
    public void setUp(){
        productRepo = Mockito.mock(ProductRepo.class);
        productService = new ProductService(productRepo);
    }

    @Test
    public void findAllProducts(){
        List<Product> products = new ArrayList<>();
        when(productRepo.findAll()).thenReturn(products);

        Assertions.assertEquals(productService.findAllProducts(), products);
    }


    @Test
    public void testUpdateProduct(){
        product = Product.builder()
                .id(2L)
                .description("updatedTest")
                .name("updatedTest")
                .price(100)
                .imageUrl("test")
                .productCode("test").build();
        productService.updateProduct(product);
    }

    @Test
    public void testDeleteProduct(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            productService.deleteProduct(null);
        });
    }

}
