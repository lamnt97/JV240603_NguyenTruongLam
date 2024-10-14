package com.example.controller;

import com.example.model.entity.Category;
import com.example.model.entity.Product;
import com.example.model.service.category.CategoryService;
import com.example.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "product/add";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute Product product, @RequestParam("imgFile") MultipartFile file, Model model) {
        // Xử lý upload ảnh
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String path = "D:\\Rikkei\\MD3\\ktr_md3\\src\\main\\webapp\\uploads\\";
            File destination = new File(path + File.separator + fileName);
            try {
                Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
                product.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Failed to upload image.");
                return "product/add";
            }
        }

        boolean created = productService.create(product);
        if (created) {
            return "redirect:/product";
        }
        model.addAttribute("error", "Failed to create product.");
        return "product/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/product";
        }
        model.addAttribute("product", product);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "product/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute Product product,
                         @RequestParam("imgFile") MultipartFile file, Model model) {
        // Tìm sản phẩm theo ID
        Product existingProduct = productService.findById(id);
        if (existingProduct == null) {
            return "redirect:/product";
        }


        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String path = "D:\\Rikkei\\MD3\\ktr_md3\\src\\main\\webapp\\uploads\\";
            File destination = new File(path + File.separator + fileName);
            try {
                Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
                product.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error", "Failed to upload image.");
                return "product/edit";
            }
        } else {

            product.setImage(existingProduct.getImage());
        }

        boolean updated = productService.update(product);
        if (updated) {
            return "redirect:/product";
        }

        model.addAttribute("product", product);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("error", "Failed to update product.");
        return "product/edit";
    }

    // Phương thức xóa sản phẩm
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/product";
    }
}