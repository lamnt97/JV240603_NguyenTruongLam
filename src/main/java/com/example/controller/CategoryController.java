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
    public String index(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "category/index";
    }
    @GetMapping("/add")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "category/add";
    }
    @PostMapping("/add")
    public String create(@ModelAttribute Category category){
        if(categoryService.create(category)){
            return "redirect:/category";
        }
        return "category/add";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,Model model){
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "category/edit";
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") int id,@ModelAttribute("category") Category category){
        if(categoryService.update(category)){
            return "redirect:/category";
        }
        return "redirect:/category/edit/{id}";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        categoryService.delete(id);
        return "redirect:/category";
    }
}