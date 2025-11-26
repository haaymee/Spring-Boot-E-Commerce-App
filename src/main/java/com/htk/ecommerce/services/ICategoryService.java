package com.htk.ecommerce.services;

import com.htk.ecommerce.models.Category;
import com.htk.ecommerce.payloads.CategoryRequestDTO;
import com.htk.ecommerce.payloads.CategoryResponseDTO;

import java.util.List;

public interface ICategoryService {
    CategoryResponseDTO GetAllCategories(int pageNumber, int PageSize, String fieldToSort, String sortOrder);
    CategoryResponseDTO CreateCategory(CategoryRequestDTO categoryRequest);
    CategoryResponseDTO UpdateCategory(Long categoryIdToUpdate, CategoryRequestDTO categoryRequest);
    CategoryResponseDTO DeleteCategory(Long categoryId);
}
