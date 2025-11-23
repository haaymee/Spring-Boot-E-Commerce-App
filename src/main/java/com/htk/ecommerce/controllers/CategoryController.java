package com.htk.ecommerce.controllers;

import com.htk.ecommerce.payloads.CategoryRequestDTO;
import com.htk.ecommerce.payloads.CategoryResponseDTO;
import com.htk.ecommerce.services.ICategoryService;
import com.htk.ecommerce.models.Category;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponseDTO>  GetAllCategories() {
        return new ResponseEntity<>(categoryService.GetAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryResponseDTO> CreateCategory(@Valid @RequestBody CategoryRequestDTO category) {
        return new ResponseEntity<>(categoryService.CreateCategory(category), HttpStatus.OK);
    }

    @PutMapping("api/public/categories/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> UpdateCategory(
        @PathVariable Long categoryId, @Valid @RequestBody CategoryRequestDTO categoryRequest
    ) {
        return new ResponseEntity<>(categoryService.UpdateCategory(categoryId, categoryRequest), HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> DeleteCategory(@PathVariable Long categoryId) {
        return new ResponseEntity<>(categoryService.DeleteCategory(categoryId), HttpStatus.OK);
    }
}
