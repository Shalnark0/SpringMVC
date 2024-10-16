package com.shop.productmanager;

import com.shop.productmanager.model.Product;
import com.shop.productmanager.repo.ProductRepo;
import com.shop.productmanager.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest // Используется для тестирования контроллеров
public class ProductResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    Product product;

    @BeforeEach
    public void setUp(){
        product = Product.builder()
                .id(2L)
                .description("test")
                .name("test")
                .price(100)
                .imageUrl("test")
                .productCode("test").build();
    }

    @Test
    @Order(1)
    public void saveProductTest() throws Exception{
        given(productService.addProduct(any(Product.class))).willReturn(product);
        ResultActions response = mockMvc.perform(post("/api/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.description", is(product.getDescription())))
                .andExpect(jsonPath("$.price", is(product.getPrice())))
                .andExpect(jsonPath("$.imageUrl", is(product.getImageUrl())))
                .andExpect(jsonPath("$.productCode", is(product.getProductCode())));
        }
    @Test
    @Order(2)
    public void getProductsTest() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(Product.builder().id(2L).name("test").price(100)
                .description("test").imageUrl("test").productCode("test").build());
        given(productService.findAllProducts()).willReturn(productList);
        ResultActions response = mockMvc.perform(get("/api/products/all"));
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(productList.size())));
    }

    @Test
    @Order(3)
    public void updateProductTest() throws Exception{
        given(productService.findProductById(product.getId())).willReturn(product);
        product.setName("updatedTest");
        product.setDescription("updatedTest");
        given(productService.updateProduct(product)).willReturn(product);

        ResultActions response = mockMvc.perform(put("/api/products/update", product)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)));
        response.andExpect((status().isOk()))
                .andDo((print())).andExpect(jsonPath("$.name", is(product.getName())))
                .andExpect(jsonPath("$.description", is(product.getDescription())));
    }

    @Test
    public void deleteProductTest() throws Exception{
        willDoNothing().given(productService).deleteProduct(product.getId());

        ResultActions response = mockMvc.perform(delete("/api/products/delete/{id}", product.getId()));

        response.andExpect(status().isOk()).andDo(print());
    }

}


