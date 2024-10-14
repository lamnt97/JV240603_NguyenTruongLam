package com.example.model.dao.category;

import com.example.model.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    boolean create(Category category);
    Category findById(int id);
    boolean update(Category category);
    void delete(int id);

}