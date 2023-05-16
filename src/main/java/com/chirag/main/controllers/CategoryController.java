package com.chirag.main.controllers;

import com.chirag.main.helpers.ApiResponse;
import com.chirag.main.dto.CategoryDto;
import com.chirag.main.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
       CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
       return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{cId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer cId, @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = this.categoryService.updateCategory(cId, categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @GetMapping("/{cId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer cId) {
        CategoryDto category = this.categoryService.getCategory(cId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }
    @DeleteMapping("/{cId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer cId) {
        this.categoryService.deleteCategory(cId);
        return new ResponseEntity<>(new ApiResponse(true, "Category Deleted Successfully"), HttpStatus.OK);
    }
}
