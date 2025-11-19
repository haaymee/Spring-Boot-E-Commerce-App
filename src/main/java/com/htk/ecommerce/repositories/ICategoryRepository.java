package com.htk.ecommerce.repositories;

import com.htk.ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {



}
