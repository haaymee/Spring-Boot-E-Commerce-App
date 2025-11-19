package com.htk.ecommerce.controllers;

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

    private ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>>  GetAllCategories() {
        return new ResponseEntity<List<Category>>(categoryService.GetAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<String> CreateCategory(@Valid @RequestBody Category category) {
        categoryService.CreateCategory(category);
        return new ResponseEntity<String>( "Category added successfully", HttpStatus.OK);
    }

    @PutMapping("api/public/categories/{categoryId}")
    public ResponseEntity<String> UpdateCategory(@PathVariable Long categoryId, @RequestBody Category categoryData) {
        String status = categoryService.UpdateCategory(categoryId, categoryData);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> DeleteCategory(@PathVariable Long categoryId) {
        String status = categoryService.DeleteCategory(categoryId);
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
}
