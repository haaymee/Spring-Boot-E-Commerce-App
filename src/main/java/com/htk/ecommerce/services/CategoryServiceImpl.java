package com.htk.ecommerce.services;

import com.htk.ecommerce.exceptions.APIException;
import com.htk.ecommerce.exceptions.ResourceNotFoundException;
import com.htk.ecommerce.models.Category;
import com.htk.ecommerce.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> GetAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void CreateCategory(Category category) {

        Category existingCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (existingCategory != null)
            throw new APIException(
                    String.format("Category %s already exists", existingCategory.getCategoryName()),
                    HttpStatus.BAD_REQUEST
            );

        categoryRepository.save(category);
    }

    @Override
    public String UpdateCategory(long categoryId, Category newCategoryData) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(categoryId)));

        category.setCategoryName(newCategoryData.getCategoryName());
        categoryRepository.save(category);

        return "Category ("+ category.getCategoryId() + ") updated successfully";
    }

    @Override
    public String DeleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream().
                filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(categoryId)));

        categoryRepository.delete(category);

        return "Category " + category.getCategoryName() +
                "(" + category.getCategoryId() + ") deleted successfully";
    }
}
