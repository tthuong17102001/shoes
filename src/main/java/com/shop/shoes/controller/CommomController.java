package com.shop.shoes.controller;

import com.shop.shoes.entities.Category;
import com.shop.shoes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class CommomController {
    @Autowired
    CategoryRepository categoryRepository;
    @ModelAttribute("categoryList")
    public List<Category> showCategory(Model model){
        List<Category> categoryList = this.categoryRepository.findAll();
        model.addAttribute("categoryList",categoryList);
        return categoryList;
    }
}
