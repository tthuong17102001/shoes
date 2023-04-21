package com.shop.shoes.controller;

import java.security.Principal;
import java.util.List;

import com.shop.shoes.entities.Category;
import com.shop.shoes.entities.User;
import com.shop.shoes.repository.CategoryRepository;
import com.shop.shoes.repository.ProductRepository;
import com.shop.shoes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CommomController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @ModelAttribute("categoryList")
    public List<Category> showCategory(Model model) {
        List<Category> categoryList = categoryRepository.findAll();
        model.addAttribute("categoryList", categoryList);

        return categoryList;
    }
    @ModelAttribute("user")
    public User user(Model model, Principal principal, User user){
        if(principal != null){
            model.addAttribute("user",new User());
            user = userRepository.findByEmail(principal.getName());
            model.addAttribute("user",user);
        }
        return user;
    }
}
