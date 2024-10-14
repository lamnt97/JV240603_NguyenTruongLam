package com.example.model.service.product;

import com.example.model.entity.Category;
import com.example.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    boolean create(Product product);
    Product findById(int id);
    boolean update(Product product);
    void delete(int id);
}