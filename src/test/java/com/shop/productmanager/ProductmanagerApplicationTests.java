package com.shop.productmanager;

import com.shop.productmanager.model.Product;
import com.shop.productmanager.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductmanagerApplicationTests {

	@Autowired
	private ProductService productService;

	@Test
	void testCreateProduct() {
		Product product = new Product("Test Product",
				"Test Product",
				0,
				"Test",
				"Test" );
		Product savedProduct = productService.addProduct(product);
		assertNotNull(savedProduct);
		assertEquals("Test Product", savedProduct.getName());
	}


	@Test
	void contextLoads() {
	}

}
