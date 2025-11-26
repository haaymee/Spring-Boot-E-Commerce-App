package com.htk.ecommerce.services;

import com.htk.ecommerce.exceptions.APIException;
import com.htk.ecommerce.exceptions.ResourceNotFoundException;
import com.htk.ecommerce.models.Category;
import com.htk.ecommerce.payloads.CategoryRequestDTO;
import com.htk.ecommerce.payloads.CategoryResponseDTO;
import com.htk.ecommerce.repositories.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponseDTO GetAllCategories(int pageNumber, int pageSize, String fieldToSortBy, String sortOrder) {

        Sort sortDetails = sortOrder.equalsIgnoreCase("asc") ?
                Sort.by(fieldToSortBy).ascending() : Sort.by(fieldToSortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortDetails);
        Page<Category> categoryPages = categoryRepository.findAll(pageDetails);

        if (categoryPages.isEmpty())
            throw new APIException("No categories have been created");

        CategoryResponseDTO response = new CategoryResponseDTO(categoryPages.stream().map(
                category -> modelMapper.map(category, CategoryRequestDTO.class)).toList());

        response.setPageNumber(categoryPages.getNumber());
        response.setPageSize(categoryPages.getSize());
        response.setTotalElements(categoryPages.getTotalElements());
        response.setTotalPages(categoryPages.getTotalPages());
        response.setLastPage(categoryPages.isLast());

        return response;
    }

    @Override
    public CategoryResponseDTO CreateCategory(CategoryRequestDTO categoryRequest) {

        Category existingCategory = categoryRepository.findByCategoryName(categoryRequest.getCategoryName());
        if (existingCategory != null)
            throw new APIException(
                    String.format("Category %s already exists", existingCategory.getCategoryName()),
                    HttpStatus.BAD_REQUEST
            );

        Category createdCategory = modelMapper.map(categoryRequest, Category.class);
        categoryRepository.save(createdCategory);

        List<CategoryRequestDTO> content = new ArrayList<>();
        content.add(modelMapper.map(createdCategory, CategoryRequestDTO.class));
        return new CategoryResponseDTO(
                content,
                String.format("Created Category %d (%s)",
                        createdCategory.getCategoryId(),
                        createdCategory.getCategoryName()
                )
        );
    }

    @Override
    public CategoryResponseDTO UpdateCategory(Long categoryIdToUpdate, CategoryRequestDTO categoryRequest) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryIdToUpdate)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(categoryIdToUpdate)));

        category.setCategoryName(categoryRequest.getCategoryName());
        categoryRepository.save(category);

        List<CategoryRequestDTO> content = new ArrayList<>();
        content.add(modelMapper.map(category, CategoryRequestDTO.class));
        return new CategoryResponseDTO(
                content,
                String.format("Updated Category %d (%s)", category.getCategoryId(), category.getCategoryName())
        );
    }

    @Override
    public CategoryResponseDTO DeleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream().
                filter(c -> c.getCategoryId().equals(categoryId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(categoryId)));

        categoryRepository.delete(category);

        List<CategoryRequestDTO> content = new ArrayList<>();
        content.add(modelMapper.map(category, CategoryRequestDTO.class));
        return new CategoryResponseDTO(
                content,
                String.format("Successfully deleted %d (%s)", category.getCategoryId(), category.getCategoryName())
        );
    }
}
