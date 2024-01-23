package com.example.lista12.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.lista12.entity.Category;
import com.example.lista12.service.CategoryService;

@Controller
public class CategoryController {
private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/category/")
    public String home(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);
        String serverTime = dateFormat.format(date);
        model.addAttribute("serverTime", serverTime);
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList );
        return "category/index";
    }
    @GetMapping("/category/add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        return "/category/add";
    }

    @GetMapping("/category/details")
    public String add(@RequestParam("id") String inputId, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(inputId));
        return "/category/details";
    }

    @GetMapping(value = {"/category/{categoryId}/edit"})
    public String edit(Model model, @PathVariable String categoryId) {
        model.addAttribute("category", categoryService.getCategoryById(categoryId));
        return "/category/edit";
    }

    @PostMapping(value = {"/category/edit"})
    public String edit (@ModelAttribute Category category) {
        categoryService.updateCategory(category);
        return "redirect:/category/";
    }

    @GetMapping("/category/remove")
    public String remove(@RequestParam("id") String id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/category/";
    }
}
