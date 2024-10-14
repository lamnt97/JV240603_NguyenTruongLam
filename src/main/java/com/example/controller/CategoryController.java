package com.example.controller;

import com.example.model.entity.Category;
import com.example.model.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/index"; // Trả về danh sách các danh mục
    }

    @GetMapping("/add")
    public String add(Model model) {
        Category category = new Category(); // Tạo danh mục mới
        model.addAttribute("category", category);
        return "category/add"; // Trả về trang thêm danh mục
    }

    @PostMapping("/add")
    public String create(@ModelAttribute Category category) {
        // Tạo danh mục mới
        if (categoryService.create(category)) {
            return "redirect:/category"; // Nếu tạo thành công, quay về trang danh sách
        }
        return "category/add"; // Nếu không thành công, quay lại trang thêm
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        // Lấy danh mục theo ID để chỉnh sửa
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/edit"; // Trả về trang chỉnh sửa
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("category") Category category) {
        category.setId(id); // Cập nhật ID của danh mục
        if (categoryService.update(category)) {
            return "redirect:/category"; // Nếu cập nhật thành công, quay về trang danh sách
        }
        return "redirect:/category/edit/" + id; // Nếu không thành công, quay lại trang chỉnh sửa
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        categoryService.delete(id); // Xóa danh mục theo ID
        return "redirect:/category"; // Quay lại trang danh sách sau khi xóa
    }
}
