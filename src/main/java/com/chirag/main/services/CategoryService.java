package com.chirag.main.services;

import com.chirag.main.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto createCategory(CategoryDto categoryDto);

    public CategoryDto getCategory(Integer cId);

    public CategoryDto updateCategory(Integer cId, CategoryDto categoryDto);

    public void deleteCategory(Integer cId);

    public List<CategoryDto> getAllCategories();
}
