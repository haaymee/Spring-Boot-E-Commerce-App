package com.htk.ecommerce.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDTO {

    private List<CategoryRequestDTO> content;
    private String message;

    private int pageNumber;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean lastPage;


    public CategoryResponseDTO(List<CategoryRequestDTO> content) {
        this.content = content;
        this.message = "";
        this.pageNumber = 0;
        this.pageSize = 0;
        this.totalElements = 0L;
        this.totalPages = 0;
        this.lastPage = true;
    }

    public CategoryResponseDTO(List<CategoryRequestDTO> content, String message) {
        this.content = content;
        this.message = message;
        this.pageNumber = 0;
        this.pageSize = 0;
        this.totalElements = 0L;
        this.totalPages = 0;
        this.lastPage = true;
    }
}
