package com.htk.ecommerce.services;

import com.htk.ecommerce.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> GetAllCategories();
    void CreateCategory(Category category);
    String UpdateCategory(long categoryId, Category newCategoryData);
    String DeleteCategory(Long categoryId);
}
