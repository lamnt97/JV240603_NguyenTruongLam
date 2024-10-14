package com.example.model.service.product;

import com.example.model.dao.category.CategoryDAO;
import com.example.model.dao.category.ProductDAO;
import com.example.model.entity.Category;
import com.example.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public boolean create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public boolean update(Product product) {
        return productDAO.update(product);
    }

    @Override
    public void delete(int id) {
        productDAO.delete(id);
    }
}