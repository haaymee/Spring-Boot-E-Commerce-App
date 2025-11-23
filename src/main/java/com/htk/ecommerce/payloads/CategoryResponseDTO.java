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

    public CategoryResponseDTO(List<CategoryRequestDTO> content) {
        this.content = content;
        this.message = "";
    }
}
