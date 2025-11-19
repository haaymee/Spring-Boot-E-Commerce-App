package com.htk.ecommerce.com.htk.ecommerce.services;

import com.htk.ecommerce.models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final List<Category> categories = new ArrayList<>();
    private static long nextId = 0;

    @Override
    public List<Category> GetAllCategories() {
        return categories;
    }

    @Override
    public void CreateCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String UpdateCategory(long categoryId, Category newCategoryData) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Resource Not Found"));

        category.setCategoryName(newCategoryData.getCategoryName());

        return "Category ("+ category.getCategoryId() + ") updated successfully";
    }


    @Override
    public String DeleteCategory(Long categoryId) {
        Category category = categories.stream().
                filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Resource Not Found"));

        return "Category " + category.getCategoryName() +
                "(" + category.getCategoryId() + ") deleted successfully";
    }
}
