package com.shop.productmanager.service;

import com.shop.productmanager.exception.CategoryNotFoundException;
import com.shop.productmanager.model.Category;
import com.shop.productmanager.repo.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    public Category updateCategory(Category category) {
        return categoryRepo.save(category);
    }

    public Category findCategoryById(final Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category by id " + id + " not found"));
    }

    public void deleteCategory(Long id) {
        if (id == null) {
            throw new NullPointerException("id can't be null");
        }
        categoryRepo.deleteById(id);
    }
}
