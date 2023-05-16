package com.chirag.main.services.Impl;

import com.chirag.main.entities.Category;
import com.chirag.main.exceptions.ResourceNotFoundException;
import com.chirag.main.dto.CategoryDto;
import com.chirag.main.repositiories.CategoyRepositry;
import com.chirag.main.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoyRepositry categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.convertToEntity(categoryDto);
        Category savedCategory = this.categoryRepository.save(category);
        return this.convertToDto(savedCategory);
    }

    @Override
    public CategoryDto getCategory(Integer cId) {
        Category category = this.categoryRepository.findById(cId).orElseThrow(() -> {
            return new ResourceNotFoundException("Category", "id", cId);
        });
        return this.convertToDto(category);
    }

    @Override
    public CategoryDto updateCategory(Integer cId, CategoryDto categoryDto) {
        Category cat = this.categoryRepository.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", cId));
        cat.setCTitle(categoryDto.getCTitle());
        cat.setCDescription(categoryDto.getCDescription());
        Category updatedCategory = this.categoryRepository.save(cat);
        return this.convertToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer cId) {
        Category category = this.categoryRepository.findById(cId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", cId));
        this.categoryRepository.delete(category);
    }
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        return categories.stream().map(this::convertToDto).collect(java.util.stream.Collectors.toList());
    }

    public CategoryDto convertToDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category convertToEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
