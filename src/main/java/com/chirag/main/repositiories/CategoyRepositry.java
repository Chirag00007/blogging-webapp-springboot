package com.chirag.main.repositiories;

import com.chirag.main.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoyRepositry extends JpaRepository<Category, Integer> {
}
